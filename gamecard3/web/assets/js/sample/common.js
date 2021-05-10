// <%@page contentType="text/html; charset=utf-8" %>
Ext.namespace('SF.ims');
Ext.namespace('SF.claim');

Ext.QuickTips.init();

/** 分页大小 */
var pageSize = 25;
/** 请求有效时间十五分钟 */
Ext.Ajax.timeout = 15 * 60 * 1000;
Ext.BLANK_IMAGE_URL = Ext.HOME + "resources/images/default/s.gif";
Ext.Msg.minWidth = 160;

/** 最大长度计算汉字处理函数 */
maxLengthOverride = function(value) {
	if (this.allowBlank == false) {
		if (value == null || value == '') {
			this.markInvalid(String.format(this.blankText, value));
			return false;
		}
	}
	var maxLen = this.maxLength;
	var maxLenText = this.maxLengthText;
	if (maxLen != null && maxLen != 'undefined' && maxLen > 0) {
		var regex = /[^\x00-\xff]/g;
		var len;
		var repalceValue = value.replace(regex, '***');
		len = repalceValue.length;
	}
	if (len > maxLen) {
		this.markInvalid(String.format(maxLenText, value));
		return false;
	}
	return true;
};
/**
 * 验证用户是否具有此网点的权限
 * 
 * @param {}
 *            deptId 要验证的网点ID
 * @param {}
 *            successCallBack 成功后执行的方法
 */
authorizedDepartment = function(deptId, successCallBack) {
	if (!deptId) {
		Ext.Msg.alert("提示", "请选择部门!");
		return;
	}
	Ext.Ajax.request({
				url : '../organization/isAuthorizedDepartment.action',
				params : {
					deptId : deptId
				},
				success : function(response, opts) {
					var success = Ext.decode(response.responseText).success;
					if (!success) {
						Ext.Msg.alert("提示", "您没有该网点的数据权限");
						return;
					}
					successCallBack();

				}.createDelegate(this)
			});
};
/** 日期验证,开始日期不能大于结束日期 */
Ext.apply(Ext.form.VTypes, {
	daterangeText : '',
	daterange : function(val, field) {

		var date = field.parseDate(val);

		if (!date) {
			this.daterangeText = String.format(field.invalidText, val,
					field.format);
			return false;
		}
		if (field.startDateField) {
			var start = Ext.getCmp(field.startDateField);
			if (!start.maxValue || (date.getTime() != start.maxValue.getTime())) {
				start.setMaxValue(date);
				start.validate();
			}
		} else if (field.endDateField) {
			var end = Ext.getCmp(field.endDateField);
			if (!end.minValue || (date.getTime() != end.minValue.getTime())) {
				end.setMinValue(date);
				end.validate();
			}
		}
		/*
		 * Always return true since we're only using this vtype to set the
		 * min/max allowed values (these are tested for after the vtype test)
		 */
		return true;
	}
});

/**
 * 雇员选择窗口
 */
SF.ims.employeeWin = Ext.extend(Ext.Window, {
	constructor : function(config) {
		var store = new Ext.data.JsonStore({
					url : '../authorization/empList.action',
					root : 'allEmp',
					totalProperty : 'empLiseSize',
					fields : ['empCode', 'empName', 'dept', 'empDutyName',
							'empTypeName', 'empGender', 'empEmail',
							'empMobile', 'empOfficephone', 'empStatus',
							'registerDate', 'logoutDate', 'empDesc',
							'changeZoneTime', 'validFlg', 'innerFlg', 'deptId',
							'deptName', 'deptCode', 'personType',
							'nationalIdentifier', 'dateOfBirth', 'sfDate',
							'cancelDate', 'effectiveDate', 'id'],
					listeners : {
						beforeload : this.beforeEmpListStoreLoad
								.createDelegate(this)
					}
				});

		config = Ext.apply({
			title : '请选择雇员',
			closeAction : 'hide',
			modal : true,
			plain : true,
			resizable : false,
			width : 600,
			height : 450,
			layout : 'border',
			items : [{
						id : 'empSearchForm',
						region : 'north',
						xtype : 'form',
						autoHeight : true,
						frame : true,
						labelWidth : 80,
						labelAlign : 'left',
						bodyStyle : 'padding: 1px 5px 0px 1px',
						items : [{
									xtype : 'fieldset',
									title : "查询条件",
									layout : 'column',
									items : [{
												columnWidth : .5,
												layout : 'form',
												items : {
													xtype : 'textfield',
													fieldLabel : "工号",
													name : 'selEmp.empCode',
													anchor : '80%'
												}
											}, {
												columnWidth : .5,
												layout : 'form',
												items : {
													xtype : 'textfield',
													fieldLabel : "职位",
													name : 'selEmp.empDutyName',
													anchor : '80%'
												}
											}, {
												columnWidth : .5,
												layout : 'form',
												items : {
													xtype : 'textfield',
													fieldLabel : "姓名",
													name : 'selEmp.empName',
													anchor : '80%'
												}
											}, {
												columnWidth : .5,
												layout : 'form',
												items : {
													xtype : 'textfield',
													fieldLabel : "电话",
													name : 'selEmp.empOfficephone',
													anchor : '80%'
												}
											}, {
												columnWidth : .5,
												layout : 'form',
												items : {
													xtype : 'textfield',
													name : 'tempDeptCode',
													fieldLabel : '部门',
													allowBlank : false,
													readOnly : true,
													anchor : '80%'
												}
											}, {
												xtype : 'hidden',
												name : 'selEmp.innerFlg',
												value : true
											}, {
												xtype : 'hidden',
												id : 'selEmp.deptId'
											}, {
												xtype : 'hidden',
												id : 'selEmp.onlyPublic',
												name : 'selEmp.onlyPublic'
											}]
								}]
					}, {
						region : 'center',
						xtype : 'grid',
						store : store,
						sm : new Ext.grid.RowSelectionModel({
									singleSelect : true
								}),
						cm : new Ext.grid.ColumnModel({
									columns : [new Ext.grid.RowNumberer(), {
												header : "工号",
												width : 70,
												dataIndex : 'empCode'
											}, {
												header : "姓名",
												width : 70,
												dataIndex : 'empName'
											}, {
												header : "身份证号",
												width : 120,
												dataIndex : 'nationalIdentifier'
											}, {
												header : "所属部门",
												width : 160,
												dataIndex : 'deptName'
											}, {
												header : "岗位",
												width : 120,
												dataIndex : 'empDutyName'
											}, {
												header : "员工类型",
												width : 80,
												dataIndex : 'personType'
											}, {
												header : "职位属性",
												width : 80,
												dataIndex : 'empTypeName'
											}]
								}),
						stripeRows : true,
						enableHdMenu : false,
						loadMask : true,
						tbar : new Ext.PagingToolbar({
									store : store,
									pageSize : pageSize,
									displayInfo : true
								}),
						listeners : {
							rowdblclick : this.select.createDelegate(this)
						}
					}],
			tbar : [{
						text : "查询",
						iconCls : 'search',
						handler : this.search.createDelegate(this)
					}, {
						text : "选择",
						iconCls : 'reset',
						handler : this.select.createDelegate(this)
					}],
			listeners : {
				show : function(window) {
					window.items.get(0).getForm().reset();
				}
			}
		}, config);
		SF.ims.employeeWin.superclass.constructor.call(this, config);
	},
	beforeEmpListStoreLoad : function(store) {
		store.baseParams = Ext.apply(Ext.getCmp('empSearchForm').getForm()
						.getValues(), {
					start : 0,
					limit : pageSize
				});
	},

	search : function() {
		var store = this.items.get(1).getStore();
		var form = this.items.get(0).getForm();
		if (!form.isValid()) {
			Ext.Msg.alert("提示", "请选择部门!");
			return;
		}
		store.load({
					callback : function(r, option, success) {
						if (!success) {
							store.removeAll();
							if (this.reader && this.reader.jsonData) {
								Ext.Msg.alert("提示", this.reader.jsonData.msg);
							}
						}
					}
				});
	},
	setLoadForm : function(form) {
		this.loadForm = form;
	},
	select : function() {
		var cm = this.items.get(1).getSelectionModel();
		var count = cm.getCount();
		if (count != 1) {
			Ext.Msg.alert("提示", "请选择一条雇员信息!");
			return;
		}
		var data = cm.getSelected().data;
		this.loadForm.setData(data);
		this.hide();
	}
});


SF.deptCode = null;
SF.deptId = null;
/**
 * 雇员选择控件
 */
SF.ims.empField = new Ext.extend(Ext.ux.form.SearchField, {
			constructor : function(cfg) {
				this.empWin = new SF.ims.employeeWin();
				Ext.getCmp('selEmp.onlyPublic')
						.setValue(!!(cfg && cfg.onlyPublic));
				this.cfg = cfg;
				if (!cfg) {
					throw "config is required!";
				}
				if (!cfg.loadForm) {
					throw "loadForm is required!";
				}
				SF.ims.empField.superclass.constructor.call(this, cfg);
			},
			initComponent : function() {
				Ext.apply(this, {
							autoCreate : {
								tag : 'input',
								type : 'text',
								size : '16',
								autocomplete : 'off',
								style : 'color:#C0C0C0;',
								onkeydown : 'return false'
							},
							listeners : {
								seachclick : this.searchEmp
										.createDelegate(this)
							}
						});
				SF.ims.empField.superclass.initComponent.call(this);
			},
			searchEmp : function(value) {
				if (!SF.deptCode) {
					throw "SF.deptCode is required!";
				}
				this.empWin.setLoadForm(this.cfg.loadForm);
				this.empWin.show();
				Ext.getCmp('selEmp.deptId').setValue(SF.deptId);
				this.empWin.items.get(0).getForm().findField('tempDeptCode')
						.setValue(SF.deptCode);
				this.empWin.items.get(1).getStore().load();
			}
		});
Ext.reg('empfield', SF.ims.empField);

/*部门树组件*/
SF.ims.TreePanel = Ext.extend(Ext.tree.TreePanel, {

	constructor : function(cfg) {
		var deptLoader = new Ext.tree.TreeLoader({
			dataUrl : "../imsmgmt/userDeptList.action?textField=deptName&idField=departmentId&leafField=&clsField=&childrenField=",
			url : "../imsmgmt/userDeptList.action?textField=deptName&idField=departmentId&leafField=&clsField=&childrenField="
		});
		var deptRoot = new Ext.tree.AsyncTreeNode({
					id : "1",
					deptCode : "001",
					text : "顺丰速运(集团)有限公司"
				});
		cfg = Ext.apply({
					region : "west",
					title : "网点信息",
					autoScroll : true,
					split : true,
					collapsible : true,
					width : 250,
					loader : deptLoader,
					root : deptRoot
				}, cfg);
		SF.ims.TreePanel.superclass.constructor.call(this, cfg);

	},
	onInitHandler : function(loader) {
		this.fireEvent('click', this.getRootNode());
	}
});

Ext.reg('SF.ims.treePanel', SF.ims.TreePanel);

/*下拉列表组件*/
SF.ComboBox = Ext.extend(Ext.form.ComboBox, {
			constructor : function(cfg) {
				if (cfg && cfg.hasAll && cfg.data) {
					if(cfg.data[0][0]){
						cfg.data.unshift(['', '全部']);
					}
				}
				cfg = Ext.apply({
							typeAhead : true,
							mode : 'local',
							editable : false,
							valueField : "key",
							displayField : "value",
							triggerAction : 'all',
							selectOnFocus : true,
							store : new Ext.data.SimpleStore({
										fields : ["key", "value"],
										data : cfg.data
									})
						}, cfg);
				SF.ComboBox.superclass.constructor.call(this, cfg);
			}

		});
Ext.reg('SF.comboBox', SF.ComboBox);



/*部门选择输入框组件*/
var selectDeptNode = null ;
var deptField = null;
// 选择所属部门窗口
var selectDeptWin = new Ext.Window({
			width : 600,
			height : 400,
			frame : true,
			closeAction : 'hide',
			layout : 'fit',
			buttons : [{
						text : "确定",
						iconCls : 'revert',
						handler : function() {
							if (!selectDeptNode) {
								Ext.MessageBox.alert("提示", "请选择所属部门");
								return;
							}
							// 权限验证
							authorizedDepartment(selectDeptNode.id, function() {
										deptField.setValue(selectDeptNode.text);
										if(Ext.getCmp('event.reportDept')){
											Ext.getCmp('event.reportDept').setValue(selectDeptNode.id);
										}
										selectDeptWin.hide();
									});
						}
					}, {
						text : "取消",
						iconCls : 'close',
						handler : function() {
							selectDeptWin.hide();
						}
					}],
			items : [{
				xtype : 'SF.ims.treePanel',
				listeners : {
					click : function(node) {
						selectDeptNode = node;
						selectDeptWin.setTitle("选择上报部门"
								+ "<font color='red'> ->" + node.text
								+ "</font>");
					}
				}
			}]
		});

SF.ims.DeptField = Ext.extend(Ext.form.TriggerField, {
			constructor : function(cfg) {
				cfg = Ext.apply({
							triggerClass : 'x-form-search-trigger',
							editable : false,
							enableKeyEvents : true
						}, cfg);
				SF.ims.DeptField.superclass.constructor.call(this, cfg);
			},
			onTriggerClick : function() {
				deptField = this;
				selectDeptWin.setTitle("选择上报部门");
				selectDeptWin.show();
			}
		});
Ext.reg('SF.ims.deptField', SF.ims.DeptField);



/*保险理赔基本信息组件*/
SF.claim.BaseInfoFieldSet = Ext.extend(Ext.form.FieldSet,{
	constructor : function(config){
		config = Ext.apply({
			title:'基本信息',
			layout:'column',
			autoHeight:true,
			defaults:{
				layout:'form',
				defaults:{
					anchor:'95%'
				}
			},
			items:[
					
						{
							columnWidth : .33,
							items : [{
									xtype:"textfield",
									name:"event.reportDeptName",
									readOnly:true,
									value:'${reportDeptName}',
									fieldLabel:"上报地区/部门"
									}]

						},
						{
							columnWidth : .33,
							items : [{
									xtype:"textfield",
									name:"event.reportUserName",
									readOnly:true,
									value:'${reportUserName}',
									fieldLabel:"上报人"
									}]
						},
						{
							columnWidth : .33,
							items : [{
									xtype:"textfield",
									name:"event.reportContact",
									allowBlank:false,
									fieldLabel:"上报人联系方式"
									}]
						},
						
						{
							columnWidth : .33,
							items : [{
									xtype:"textfield",
									name:"event.caseOccourtime",
									allowBlank:false,
									fieldLabel:"案件发生时间",
									listeners: {
				                        focus: function(){
				                       		 WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm'});
				                        }
				                    }
									}]
						},
						{
							columnWidth : .33,
							items : [{
									xtype:"textfield",
									name:"event.caseFindtime",
									allowBlank:false,
									fieldLabel:"案件发现时间",
									listeners: {
				                        focus: function(){
				                       		 WdatePicker({skin:'default',dateFmt:'yyyy-MM-dd HH:mm'});
				                        }
				                    }
									}]
						},
						{
							columnWidth : .33,
							items : [{
									xtype:"textfield",
									name:"event.casePlace",
									allowBlank:false,
									fieldLabel:"发生地点"
									}]
						},
						{
							columnWidth : 1,
							items : [{
									xtype:'textarea',
									allowBlank:false,
									name:"event.caseDesc",
									fieldLabel:"案件描述",
									anchor:'97%'
									}]
						},{
					xtype:'hidden',
					name:'event.eventId'
			}
		         
			]
			
		},config);
		SF.claim.BaseInfoFieldSet.superclass.constructor.call(this,config);
	}
});

Ext.reg('SF.claim.baseInfoFieldSet',SF.claim.BaseInfoFieldSet);


/**
 * 重写Grid.Column.renderer 每列悬停时提示内容信息
 */
Ext.override(Ext.grid.Column, {
			renderer : function(value, metadata, record, rowIdx, colIdx, ds) {
				if (this.rendererCall) {
					var ret = this.rendererCall(value, metadata, record,
							rowIdx, colIdx, ds);
					if (this.header == '备注' || this.header == '保险分类') {
						return '<div ext:qtitle="' + this.header
								+ '" ext:qtip="' + (ret == null ? "" : ret)
								+ '">' + (ret == null ? "" : ret) + '</div>';
					}
					return ret;
				} else {
					if (this.header == '备注' || this.header == '保险分类') {
						return '<div ext:qtitle="' + this.header
								+ '" ext:qtip="' + (value == null ? "" : value)
								+ '">' + (value == null ? "" : value)
								+ '</div>';
					}
					return value;
				}
			}
		});

/**
 * 重写GridPanel 隐藏列表头的头部下拉菜单
 */
Ext.override(Ext.grid.GridPanel, {
			enableHdMenu : false
		});

/**
 * 重写Ext.grid.RowNumberer 使GRID分页时实现序号自增
 */
/*
 * Ext.override(Ext.grid.RowNumberer,{ renderer : function(value, cellmeta,
 * record, rowIndex, columnIndex,store) { if(store.lastOptions &&
 * store.lastOptions.params){ return store.lastOptions.params.start + rowIndex +
 * 1; } return rowIndex + 1; } });
 */

/** 日期格式 yyyy-MM-dd H:i */
Ext.renderDate = function(value) {
	if (value != null) {
		value = value.replace("T", " ");
		if (value.length == 19) {
			value = value.substr(0, 16);
		}
	}
	return value;
};




/**
 * 给所有设置为readOnly的输入灰色显示
 */
Ext.override(Ext.form.TriggerField, {
			setReadOnly : function(readOnly) {
				if (readOnly != this.readOnly) {
					this.readOnly = readOnly;
					this.updateEditState();
				}
				if (this.el) {
					if (this.readOnly) {
						this.el.setStyle('color', "#C0C0C0");
					} else {
						this.el.setStyle('color', "#000000");
					}
				}
			}
		});
