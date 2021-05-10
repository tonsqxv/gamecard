<%@ page language="java" contentType="text/html; charset=utf-8"%>
Ext.onReady(function() {
			var gridStore = new Ext.data.JsonStore({
				url : 'insureEmp_listPageBy.action',
				fields : [ {name : 'insureId'}, 
				           {name : 'insureNo'}, 
				           {name : 'insureCompany'}, 
				           {name : 'areaName'}, 
				           {name : 'deptName'},
				           {name : 'deptId'},
				           {name : 'empCode'}, 
				           {name : 'empName'},
				           {name : 'gender'	},
				           {name : 'borthday'}, 
				           {name : 'idcard'}, 
				           {name : 'divisionTm'},
				           {name : 'persionType'},
				           {name : 'dutyName'}, 
				           {name : 'dutyType'},
				           {name : 'review'} ,
				           {name : 'status'	} , 
				           {name : 'planEffectTm'} ,
				           {name : 'effectTm'} ,
				           {name : 'planLoseTm'} ,
				           {name : 'loseTm' } , 
				           {name : 'descipt'} ,
				           {name : 'createEmp'} ,
				           {name : 'createEmpName'} ,
				           {name : 'createTm'} ,
				           {name : 'updateEmp'} ,
				           {name : 'updateEmpName'} ,
				           {name : 'updateTm'} ,
				           {name : 'days'} ,
				           {name : 'totalInsureFee'} ,
				           {name : 'feeType'} ,
				           {name : 'version'}
				],
				totalProperty : 'totalSize',
				root : 'root'
			});

			var dispalyDateFormat = new Ext.form.DateField({
				maxLength : 20,
				width : 120,
				disabled : false,
				format : "Y-m-d"
			});
			
			var dispalyDateFormat2 = new Ext.form.DateField({
				maxLength : 20,
				width : 120,
				disabled : false,
				format : "Y-m-d H:i:s"
			});
			
			var his_node; // 保存选择的网点节点
			var queryForm = new Ext.form.FormPanel({
				frame : true,
				region : "north",
				autoHeight : true,
				fileUpload:true,
				items : [

				new Ext.form.FieldSet({
					labelWidth : 100,
					//height : 190,
					autoHeight:true,
					layout : "column",
					title : "查询条件",
					layout : 'column',
					items : [
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : {
									xtype : "numberfield",
									name : "insureEmp.insureNo",
									width : 110,
									fieldLabel : "投保编号"
								}
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : {
									xtype : "textfield",
									name : "insureEmp.empCode",
									width : 110,
									fieldLabel : "工号"
								}
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : {
									xtype : "textfield",
									name : "insureEmp.empName",
									width : 110,
									fieldLabel : "姓名"
								}
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : {
									xtype : "textfield",
									name : "insureEmp.idcard",
									width : 110,
									fieldLabel : "身份证号码"
								}
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [ {
									xtype : "datefield",
									format : 'Y-m-d',
									name : "insureEmp.planEffectTmBegin",
									fieldLabel : '拟生效日期（开始）',
									width : 110
								}]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [ {
									xtype : "datefield",
									format : 'Y-m-d',
									name : "insureEmp.planEffectTmEnd",
									fieldLabel : '拟生效日期（结束）',
									width : 110
								}]
							} , 
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : {
									xtype : "textfield",
									name : "insureEmp.insureCompany",
									width : 110,
									fieldLabel : "投保单位"
								}
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [ {
									xtype : "datefield",
									format : 'Y-m-d',
									name : "insureEmp.effectTmBegin",
									fieldLabel : '生效日期（开始）',
									width : 110
								}]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [ {
									xtype : "datefield",
									format : 'Y-m-d',
									name : "insureEmp.effectTmEnd",
									fieldLabel : '生效日期（结束）',
									width : 110
								}]
							} , 
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : {
									xtype : "combo",
									hiddenName : "insureEmp.review",
									fieldLabel : "是否追溯",
									typeAhead : true,
									mode : 'local',
									width : 110,
									editable : false,
									displayField : "text",
									valueField : "key",
									triggerAction : 'all',
									emptyText : '全部',
									selectOnFocus : true,
									store : new Ext.data.SimpleStore({
										fields : [ "key", "text" ],
										data : [ [ '', '全部' ], [ '1', '是' ],	[ '0', '否' ] ]
									})
								}
							}, 
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [ {
									xtype : "datefield",
									format : 'Y-m-d',
									name : "insureEmp.planLoseTmBegin",
									fieldLabel : '拟失效日期（开始）',
									width : 110
								}]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [ {
									xtype : "datefield",
									format : 'Y-m-d',
									name : "insureEmp.planLoseTmEnd",
									fieldLabel : '拟失效日期（结束）',
									width : 110
								}]
							} , 
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : {
									xtype : "combo",
									hiddenName : "insureEmp.status",
									fieldLabel : "状态",
									typeAhead : true,
									mode : 'local',
									width : 110,
									editable : false,
									displayField : "text",
									valueField : "key",
									triggerAction : 'all',
									emptyText : '全部',
									selectOnFocus : true,
									store : new Ext.data.SimpleStore({
										fields : [ "key", "text" ],
										data : [ [ '', '全部' ], [ '1', '拟生效' ],[ '2', '生效' ], [ '3', '拟失效' ],[ '4', '失效' ] ]
									})
								}
							}, 
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [ {
									xtype : "datefield",
									format : 'Y-m-d',
									name : "insureEmp.loseTmBegin",
									fieldLabel : '失效日期（开始）',
									width : 110
								}]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [ {
									xtype : "datefield",
									format : 'Y-m-d',
									name : "insureEmp.loseTmEnd",
									fieldLabel : '失效日期（结束）',
									width : 110
								},{
									xtype : 'hidden',
									name : 'insureEmp.deptId'
								}]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : {
									xtype : "combo",
									hiddenName : "insureEmp.feeType",
									fieldLabel : "费用类型",
									typeAhead : true,
									mode : 'local',
									width : 110,
									editable : false,
									displayField : "text",
									valueField : "key",
									triggerAction : 'all',
									emptyText : '全部',
									selectOnFocus : true,
									store : new Ext.data.SimpleStore({
										fields : [ "key", "text" ],
										data : [ [ '', '全部' ], [ '1', '公费' ],	[ '2', '自费' ] ]
									})
								}
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : {
									xtype : "combo",
									hiddenName : "insureEmp.containSubdept",
									fieldLabel : "是否包含子网点",
									typeAhead : true,
									mode : 'local',
									width : 110,
									editable : false,
									displayField : "text",
									valueField : "key",
									triggerAction : 'all',
									emptyText : '全部',
									value:'1',
									selectOnFocus : true,
									store : new Ext.data.SimpleStore({
										fields : [ "key", "text" ],
										data : [[ '1', '是' ],	[ '0', '否' ] ]
									})
								}
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : {
									xtype : "combo",
									hiddenName : "insureEmp.reduceFlg",
									fieldLabel : "减保过滤",
									typeAhead : true,
									mode : 'local',
									width : 110,
									editable : false,
									displayField : "text",
									valueField : "key",
									triggerAction : 'all',
									emptyText : '全部',
									selectOnFocus : true,
									store : new Ext.data.SimpleStore({
										fields : [ "key", "text" ],
										data : [ [ '', '全部' ], [ '1', '有工号' ],	[ '2', '无工号' ] ]
									})
								}
							},{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [
									{
										text : "重置",
										xtype : "button",
										iconCls : 'revert',
										handler : function(){queryForm.getForm().reset() ;}
									}
								]
							}
							
					]
				}) ]
			});

			function checkboxRenderer(v, p, r) {
				var trueValue = eval(this.id + '.trueValue');
				p.css += ' x-grid3-check-col-td';
				return '<div class="x-grid3-check-col'
						+ ((v == trueValue) ? '-on' : '') + ' x-grid3-cc-'
						+ this.id + '">&#160;</div>';
			}

			// 日期显示格式
			var dispalyDateFormat = new Ext.form.DateField({
					maxLength:20,
					width:120,
					disabled:false,
					format:"Y-m-d"});
			
			function checkboxRenderer(v, p, r) {
					var trueValue = eval(this.id + '.trueValue');
					p.css += ' x-grid3-check-col-td';
					return '<div class="x-grid3-check-col'+((v==trueValue)?'-on':'')+' x-grid3-cc-'+this.id+'">&#160;</div>';
					}
			// 日期显示格式
			function datetimeRenderer(v, p, r) {
				if ((v == null) || (v.length < 19))
					return v;
				var dateFormat = eval(this.id + '.format');
				if (typeof (v) == "string") {
					v = v.replace('T', ' ');
					v = Date.parseDate(v, "Y-m-d H:i:s");
				}
				return v.format(dateFormat);
			}

		
			// 下拉框处理函数
			function comboboxRenderer(v, p, r) {
				if (v != null) {
					var records = eval(this.id
							+ '.initialConfig.store.getRange()');
					var keyField = eval(this.id + '.valueField');
					var displayField = eval(this.id + '.displayField');
					for ( var i = 0; i < records.length; i++) {
						var record = records[i];
						if (record.get(keyField) == v) {
							return record.get(displayField);
						}
					}
				}
				return v;
			}

			var pagingBar = new Ext.PagingToolbar(
					{
						displayInfo : true,
						displayMsg : "当前显示 {0} - {1} 总记录数目 {2}",
						store : gridStore,
						pageSize : pageSize,
						emptyMsg : "未检索到数据"
					});
			var checkboxSelectionMode = new Ext.grid.CheckboxSelectionModel({});
			var gridPanel = new Ext.grid.GridPanel({
				region : 'center',
				loadMask:true,
				tbar : pagingBar,
				store : gridStore,
				sm : checkboxSelectionMode,
				enableHdMenu : false,
				// stripeRows : true,
				cm : new Ext.grid.ColumnModel([ new Ext.grid.RowNumberer(),
						checkboxSelectionMode, 
						{header : '投保编号',width: 100,dataIndex : "insureNo",	sortable : false},
						{header : '状态',width: 60,	dataIndex : "status",renderer:statusRender,sortable : false} , 
						{header : '所属地区',width: 140,dataIndex : "areaName",	sortable : false}, 
						{header : '投保单位',width: 100,dataIndex : "insureCompany",sortable : false},
						{header : '所属部门',width: 140,dataIndex : "deptName",	sortable : false}, 
						{header : '工号',width: 60,	dataIndex : "empCode",	sortable : false}, 
						{header : '姓名',width: 60,	dataIndex : "empName",	sortable : false}, 
						{header : '身份证号码',width: 140,dataIndex : "idcard",	sortable : false}, 
						{header : '性别',width: 40,	dataIndex : "gender",	sortable : false}, 
						{header : '出生日期',width: 80,dataIndex : "borthday",renderer:datetimeRenderer,sortable : false,id:"dispalyDateFormat"},  
						{header : '入司时间',width: 80,dataIndex : "divisionTm",renderer:datetimeRenderer,sortable : false,id:"dispalyDateFormat"},  
						{header : '人员类型',width: 80,dataIndex : "persionType",sortable : false}, 
						{header : '职位名称',width: 80,dataIndex : "dutyName",	sortable : false}, 
						{header : '职位属性',width: 80,dataIndex : "dutyType",	sortable : false},
						{header : '是否追溯',width: 60,dataIndex : "review",renderer:reviewRenderer,	sortable : false} , 
						{header : '费用类型',width: 60,dataIndex : "feeType",renderer:feeTypeRenderer,	sortable : false} , 
						{header : '拟生效日期',width: 80,dataIndex : "planEffectTm",renderer:datetimeRenderer,sortable : false,id:"dispalyDateFormat"},  
						{header : '生效日期',width: 80,dataIndex : "effectTm",	renderer:datetimeRenderer,sortable : false,id:"dispalyDateFormat"},  
						{header : '拟失效日期',width: 80,dataIndex : "planLoseTm",renderer:datetimeRenderer,sortable : false,id:"dispalyDateFormat"},  
						{header : '失效日期',width: 80,dataIndex : "loseTm",renderer:datetimeRenderer,sortable : false,id:"dispalyDateFormat"},  
						{header : '在保天数',width: 60,dataIndex : "days",	sortable : false} ,
						{header : '保费(元)',width: 60,dataIndex : "totalInsureFee",	sortable : false} ,
						{header : '备注',width: 150,dataIndex : "descipt",	sortable : false} ,
						{header : '创建人',width: 60,dataIndex : "createEmpName",sortable : false} ,
						{header : '创建时间',width: 130,dataIndex : "createTm",renderer:datetimeRenderer,sortable : false,id:"dispalyDateFormat2"} ,
						{header : '修改人',width: 60,dataIndex : "updateEmpName",sortable : false} ,
						{header : '修改时间',width: 130,dataIndex : "updateTm",renderer:datetimeRenderer,sortable : false,id:"dispalyDateFormat2"} 
						]),
			listeners:{
				rowdblclick:onDetail
			}
			});
			
			// 状态显示转换
			function statusRender(v){
				var value = '' ;
				if(v == '1'){
					value = '拟生效' ;
				}else if(v == '2'){
					value = '生效' ;
				}else if(v == '3'){
					value = '拟失效' ;
				}else if(v == '4'){
					value = '失效' ;
				}
				return  value ;
			}
			// 是否追溯显示转换
			function reviewRenderer(v){
				var value = '' ;
				if(v == '1'){
					value = '是' ;
				}else if(v == '0'){
					value = '否' ;
				}
				return  value ;
			}
			// 费用类型显示转换
			function feeTypeRenderer(v){
				var value = '' ;
				if(v == '1'){
					value = '公费' ;
				}else if(v == '2'){
					value = '自费' ;
				}
				return  value ;
			}
		
			
			
				// 投保的表单属性
			var editForm_atrr_1 = new Ext.form.TextField({width:150,name:"insureEmp.insureNo",readOnly:true,allowBlank:true,fieldLabel:"投保编号"});

			var editForm_atrr_2 = new Ext.form.TextField({width:150,name:"insureEmp.insureCompany",allowBlank:false,maxLength:100,fieldLabel:"投保单位",labelSeparator:'<font color=red>*</font>'});
			
			var editForm_atrr_3 = new Ext.form.TextField({width:150,name:"insureEmp.empName",allowBlank:false,maxLength:100,fieldLabel:"姓名",labelSeparator:'<font color=red>*</font>'});
			
			var editForm_atrr_4 = new Ext.form.ComboBox({hiddenName:"insureEmp.gender",allowBlank:false,store:new Ext.data.SimpleStore({data: [['男','男'],['女','女']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureEmp.gender",triggerAction:"all",fieldLabel:"性别",editable:false,mode:"local",labelSeparator:'<font color=red>*</font>'});

			var editForm_atrr_5 = new Ext.form.DateField({width:150,name:"insureEmp.borthday",allowBlank:false,format:"Y-m-d",fieldLabel:"出生日期",labelSeparator:'<font color=red>*</font>'});

			var editForm_atrr_6 = new Ext.form.TextField({width:150,name:"insureEmp.idcard",allowBlank:false,minLength:15,maxLength:18,fieldLabel:"身份证号码",validator:function(value){
				 var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
				   if(reg.test(value) === true){  
				      return  true;    
				      }  
				   return "身份证格式错误,长度为15或18，例如432524198811228或432524198811228888或43252419881122888X" ;
			},labelSeparator:'<font color=red>*</font>'});
			
			var editForm_atrr_7 = new Ext.form.DateField({width:150,name:"insureEmp.divisionTm",allowBlank:false,format:"Y-m-d",fieldLabel:"入司时间",labelSeparator:'<font color=red>*</font>'});

			var editForm_atrr_8 = new Ext.form.ComboBox({hiddenName:"insureEmp.persionType",allowBlank:false,store:new Ext.data.SimpleStore({data: [['全日制员工','全日制员工'],['劳务派遣','劳务派遣'],['非全日制','非全日制'],['实习生','实习生'],['勤工助学','勤工助学'],['基地见习生','基地见习生'],['其他','其他']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureEmp.persionType",triggerAction:"all",fieldLabel:"人员类型",editable:false,mode:"local",labelSeparator:'<font color=red>*</font>'});

			var editForm_atrr_9 = new Ext.form.TextField({width:100,name:"insureEmp.deptName",allowBlank:false,fieldLabel:"所属部门",labelSeparator:'<font color=red>*</font>'});

			var editForm_atrr_10 = new Ext.form.TextField({width:150,name:"insureEmp.dutyName",allowBlank:false,fieldLabel:"职位名称",maxLength:100,labelSeparator:'<font color=red>*</font>'});

			var editForm_atrr_11 = new Ext.form.ComboBox({hiddenName:"insureEmp.dutyType",allowBlank:false,store:new Ext.data.SimpleStore({data: [['一线','一线'],['二线','二线'],['三线','三线']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureEmp.dutyType",triggerAction:"all",fieldLabel:"职位属性",editable:false,mode:"local",labelSeparator:'<font color=red>*</font>'});

			var editForm_atrr_12 = new Ext.form.DateField({width:150,name:"insureEmp.planEffectTm",allowBlank:false,format:"Y-m-d",fieldLabel:"拟生效日期",labelSeparator:'<font color=red>*</font>'});

			var editForm_atrr_13 = new Ext.form.DateField({width:150,name:"insureEmp.effectTm",format:"Y-m-d",fieldLabel:"生效日期"});

			var editForm_atrr_14 = new Ext.form.ComboBox({hiddenName:"insureEmp.review",allowBlank:false,store:new Ext.data.SimpleStore({data: [['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureEmp.review",value:"0",triggerAction:"all",fieldLabel:"是否追溯",editable:false,mode:"local",labelSeparator:'<font color=red>*</font>'});
			
			var editForm_atrr_15 = new Ext.form.ComboBox({hiddenName:"insureEmp.feeType",allowBlank:false,store:new Ext.data.SimpleStore({data: [['1','公费'],['2','自费']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureEmp.feeType",value:"1",triggerAction:"all",fieldLabel:"费用类型",editable:false,mode:"local",labelSeparator:'<font color=red>*</font>'});

			var editForm_atrr_16 = new Ext.form.TextArea({height:150,width:750,name:"insureEmp.descipt",allowBlank:true,maxLength:1300,fieldLabel:"备注"});
			
			var editForm_atrr_17 = new Ext.form.Hidden({name:"insureEmp.insureId"});
			
			var editForm_atrr_18 = new Ext.form.Hidden({name:"insureEmp.deptId"});
			
			
			// 有工号修改
			function preUpdateHasEmpCode(){
				all_editForm_atrr_1.setReadOnly(true); 
				all_editForm_atrr_2.setReadOnly(false); // 投保单位
				all_editForm_atrr_3.setReadOnly(false); // 姓名
				all_editForm_atrr_4.setReadOnly(true);  // 性别
				all_editForm_atrr_5.setReadOnly(true);  // 出生日期
				all_editForm_atrr_6.setReadOnly(false); // 身份证号码
				all_editForm_atrr_7.setReadOnly(true);	// 入司时间
				all_editForm_atrr_8.setReadOnly(true);	// 人员类型
				all_editForm_atrr_9.setReadOnly(true); 	// 所属部门
				all_editForm_atrr_10.setReadOnly(true);	// 职位名称
				all_editForm_atrr_11.setReadOnly(true);	// 职位属性
				all_editForm_atrr_12.setReadOnly(false); // 拟生效日期
				all_editForm_atrr_13.setReadOnly(true); // 生效日期
				all_editForm_atrr_14.setReadOnly(false); // 是否追溯
				all_editForm_atrr_15.setReadOnly(false); // 费用类型
				all_editForm_atrr_16.setReadOnly(false); // 备注
				
				all_selectDeptBtn.disable() ;
				
				//有工号投保Tab置灰
				//Ext.getCmp("withEmpTabId").setDisabled(true);
				//tabPain.setActiveTab(1) ;
			}
			// 无工号修改
			function preUpdateNoEmpCode(){
				all_editForm_atrr_1.setReadOnly(true); 
				all_editForm_atrr_2.setReadOnly(false); // 投保单位
				all_editForm_atrr_3.setReadOnly(false); // 姓名
				all_editForm_atrr_4.setReadOnly(false);  // 性别
				all_editForm_atrr_5.setReadOnly(false);  // 出生日期
				all_editForm_atrr_6.setReadOnly(false); // 身份证号码
				all_editForm_atrr_7.setReadOnly(false);	// 入司时间
				all_editForm_atrr_8.setReadOnly(false);	// 人员类型
				all_editForm_atrr_9.setReadOnly(true); 	// 所属部门
				all_editForm_atrr_10.setReadOnly(false);	// 职位名称
				all_editForm_atrr_11.setReadOnly(false);	// 职位属性
				all_editForm_atrr_12.setReadOnly(false); // 拟生效日期
				all_editForm_atrr_13.setReadOnly(true); // 生效日期
				all_editForm_atrr_14.setReadOnly(false); // 是否追溯
				all_editForm_atrr_15.setReadOnly(false); // 费用类型
				all_editForm_atrr_16.setReadOnly(false); // 备注
				
				all_selectDeptBtn.enable() ;
				//有工号投保Tab置灰
				//Ext.getCmp("withEmpTabId").setDisabled(true);
				
				//tabPain.setActiveTab(1) ;
				
			}
			// 新增时只容许部分属性可以修改
			function preAdd(){
				editForm_atrr_1.setReadOnly(true); 
				editForm_atrr_2.setReadOnly(false); // 投保单位
				editForm_atrr_3.setReadOnly(false); // 姓名
				editForm_atrr_4.setReadOnly(false);  // 性别
				editForm_atrr_5.setReadOnly(false);  // 出生日期
				editForm_atrr_6.setReadOnly(false); // 身份证号码
				editForm_atrr_7.setReadOnly(false);	// 入司时间
				editForm_atrr_8.setReadOnly(false);	// 人员类型
				editForm_atrr_9.setReadOnly(true); 	// 所属部门
				editForm_atrr_10.setReadOnly(false);	// 职位名称
				editForm_atrr_11.setReadOnly(false);	// 职位属性
				editForm_atrr_12.setReadOnly(false); // 拟生效日期
				editForm_atrr_13.setReadOnly(true); // 生效日期
				editForm_atrr_14.setReadOnly(false); // 是否追溯
				editForm_atrr_15.setReadOnly(false); // 费用类型
				editForm_atrr_16.setReadOnly(false); // 备注
				
				selectDeptBtn.enable() ;
				
				Ext.getCmp("withEmpTabId").setDisabled(false);
				
			}
			
			// 生效变更
			function preUpdateSxbg(){
				all_editForm_atrr_1.setReadOnly(true); 
				all_editForm_atrr_2.setReadOnly(true); // 投保单位
				all_editForm_atrr_3.setReadOnly(false); // 姓名
				all_editForm_atrr_4.setReadOnly(true);  // 性别
				all_editForm_atrr_5.setReadOnly(true);  // 出生日期
				all_editForm_atrr_6.setReadOnly(false); // 身份证号码
				all_editForm_atrr_7.setReadOnly(true);	// 入司时间
				all_editForm_atrr_8.setReadOnly(true);	// 人员类型
				all_editForm_atrr_9.setReadOnly(true); // 所属部门
				all_editForm_atrr_10.setReadOnly(true);	// 职位名称
				all_editForm_atrr_11.setReadOnly(true);	// 职位属性
				all_editForm_atrr_12.setReadOnly(true); // 拟生效日期
				all_editForm_atrr_13.setReadOnly(true); // 生效日期
				all_editForm_atrr_14.setReadOnly(true); // 是否追溯
				all_editForm_atrr_15.setReadOnly(true); // 费用类型
				all_editForm_atrr_16.setReadOnly(true); // 备注
				
				all_selectDeptBtn.disable() ;
				//有工号投保Tab置灰
				//Ext.getCmp("withEmpTabId").setDisabled(true);
				//tabPain.setActiveTab(1) ;
			}
			
			var selectDeptBtn = new Ext.Button({text:"部门",iconCls:'revert',handler:onSelectDept}) ;
			
			var editForm = new Ext.form.FormPanel({region:"center",height:200,frame:true,fileUpload:false,autoWidth:true,timeout:300
				,items: [
					new Ext.Panel({layout:"column",
						items: [
							new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_1]})
							,new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_2]})
							,new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_3]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [	editForm_atrr_4]})
							,new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_5]})
							,new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_6]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [	editForm_atrr_7]})
							,new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_8]})
							,new Ext.Panel({width:210,layout:"form",items: [editForm_atrr_9]})
							,new Ext.Panel({width:90,layout:"form",items: [	selectDeptBtn]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [	editForm_atrr_10]})
							,new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_11]})
							,new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_12]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [	editForm_atrr_13]})
							,new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_14]})
							,new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_15]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:950,layout:"form",items: [editForm_atrr_16,editForm_atrr_17,editForm_atrr_18]})
							
						]
					})
				]

			});
			
			
			//无工号保存按钮
			var saveBtn1 = new Ext.Button({text:"保存",iconCls:'save',handler:onSaveOrUpdate}) ;
			
			//选择所属部门
			function onSelectDept(){
				selectDeptWin.setTitle("选择所属部门");
				selectDeptWin.show() ;
				
				selectDeptNode = null ;
			}
			var selectDeptNode ;
			//选择所属部门窗口
			var selectDeptWin = new Ext.Window({
				width:600,
				height:400,
				frame:true,
				closeAction:'hide',
				layout:'fit',
				buttons: [
				          	{text:"确定",iconCls:'revert',handler:function(){
				          		if(selectDeptNode == null){
			          				Ext.MessageBox.alert("提示","请选择所属部门");
			          				return ;
			          			}
				          		//权限验证
				          		authorizedDepartment(selectDeptNode.id,function(){
				          			if (operator_type == 'add') {
				          				editForm.getForm().findField("insureEmp.deptName").setValue(selectDeptNode.text);
					          			editForm.getForm().findField("insureEmp.deptId").setValue(selectDeptNode.id);
				    				} else if (operator_type == 'update' || operator_type == 'updateEffect') {
				    					all_editForm.getForm().findField("insureEmp.deptName").setValue(selectDeptNode.text);
					          			all_editForm.getForm().findField("insureEmp.deptId").setValue(selectDeptNode.id);
				    				}
				          			
				          			selectDeptWin.hide() ;
								
							 }.createDelegate(this));
				          	}},
							{text:"取消",iconCls:'close',handler:function() {selectDeptWin.hide();}
				}],
				items:[{
					xtype:'SF.ims.treePanel',
					listeners : {
						click : function(node) {
							selectDeptNode = node ;
							selectDeptWin.setTitle("选择所属部门"+"<font color='red'> ->"+node.text+"</font>");
						}
					}		
				}
				]
			});
			
			/******************有工号投保********************************/
			var withEmp_centerStore = new Ext.data.JsonStore(
					{	//autoLoad : true,
						url : '../authorization/empList.action',
						fields : [ {name:'empCode'}, 
						           {name:'empName'},
						           {name:'dept'}, 
						           {name:'empDutyName'},
						           {name:'empTypeName'},
							       {name:'empGender'},
							       {name:'empEmail'},
							       {name:'empMobile'},
							       {name:'empOfficephone'},
							       {name:'empStatus'},
							       {name:'registerDate'},
							       {name:'logoutDate'},
							       {name:'empDesc'},
							       {name:'changeZoneTime'},
							       {name:'validFlg'},
							       {name:'innerFlg'},
							       {name:'deptId'},
							       {name:'deptName'},
							       {name:'deptCode'},
							       {name:'personType'},
							       {name:'nationalIdentifier'},
							       {name:'dateOfBirth'},
							       {name:'sfDate'},
							       {name:'cancelDate'},
							       {name:'effectiveDate'},
							       {name:'id'},
						           ],
						root : 'allEmp',
						totalProperty:'empLiseSize',
						listeners:{'beforeload':function(store){
				    		store.baseParams["limit"]=withEmp_pagingBar.pageSize;
				    		store.baseParams["selEmp.deptId"]=his_node.id;
					    }
					}
				});
			//有工号提交form属性
			var withEmp_addForm_attr_1 = new Ext.form.TextField({width:150,name:"insureEmp.insureCompany",allowBlank:false,maxLength:100,fieldLabel:"投保单位",labelSeparator:'<font color=red>*</font>'});
			var withEmp_addForm_attr_2 = new Ext.form.DateField({width:150,name:"insureEmp.planEffectTm",allowBlank:false,format:"Y-m-d",fieldLabel:"拟生效日期",labelSeparator:'<font color=red>*</font>'});
			var withEmp_addForm_attr_3 = new Ext.form.ComboBox({hiddenName:"insureEmp.review",allowBlank:false,store:new Ext.data.SimpleStore({data: [['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureHirer.review",value:"0",triggerAction:"all",fieldLabel:"是否追溯",editable:false,mode:"local",labelSeparator:'<font color=red>*</font>'});
			var withEmp_addForm_attr_4 = new Ext.form.Hidden({name:"ids"});
			var withEmp_addForm_attr_5 = new Ext.form.Hidden({name:"insureEmp.feeType"});//费用类型设置为公费
			
			//有工号提交form
			var withEmp_addForm = new Ext.form.FormPanel({
				frame:true,
				region:"north",
				height:80,
				items: [
					new Ext.Panel({layout:"column",
						items: [
							new Ext.Panel({width:300,layout:"form",items: [withEmp_addForm_attr_1]})
							,new Ext.Panel({width:300,layout:"form",items: [withEmp_addForm_attr_2]})
							,new Ext.Panel({width:300,layout:"form",items: [withEmp_addForm_attr_3,withEmp_addForm_attr_4,withEmp_addForm_attr_5]})
						]
					})
			]});
			//有工号员工查询form属性
			var withEmp_queryForm_attr_1 = new Ext.form.TextField({width:150,name:"selEmp.empCode",fieldLabel:"工号"});
			var withEmp_queryForm_attr_2 = new Ext.form.TextField({width:150,name:"selEmp.empDutyName",fieldLabel:"职位"});
			var withEmp_queryForm_attr_3 = new Ext.form.TextField({width:150,name:"selEmp.empName",fieldLabel:"姓名"});
			var withEmp_queryForm_attr_4 = new Ext.form.Hidden({name:"selEmp.deptId"});
			var withEmp_queryForm_attr_5 = new Ext.form.Hidden({name:"selEmp.innerFlg"});
			var withEmp_queryForm_attr_6 = new Ext.form.Hidden({name:"selEmp.onlyPublic"});
			
			//有工号员工查询form
			var withEmp_queryForm = new Ext.form.FormPanel({
				frame:true,
				region:"north",
				height:60,
				title:"查询条件",
				items: [
					new Ext.Panel({layout:"column",
						items: [
							new Ext.Panel({width:300,layout:"form",items: [withEmp_queryForm_attr_1]})
							,new Ext.Panel({width:300,layout:"form",items: [withEmp_queryForm_attr_2]})
							,new Ext.Panel({width:300,layout:"form",items: [withEmp_queryForm_attr_3,withEmp_queryForm_attr_4,withEmp_queryForm_attr_5,withEmp_queryForm_attr_6]})
						]
					})
			]});
			
			var withEmp_CK = new Ext.grid.CheckboxSelectionModel({ });
			var withEmp_pagingBar = new Ext.PagingToolbar({
				displayInfo:true,
				displayMsg:"当前显示 {0} - {1} 总记录数目 {2}",
				store:withEmp_centerStore,
				pageSize:500,
				emptyMsg:"未检索到数据"
				});
			var withEmp_centerPanel = new Ext.grid.GridPanel(
					{
						//split : true,
						width : 938,
						height: 263,
						region : 'center',
						loadMask:true,
						store : withEmp_centerStore,
						tbar:withEmp_pagingBar,
						sm : withEmp_CK,
						enableHdMenu : false,
						cm : new Ext.grid.ColumnModel(
								[
										new Ext.grid.RowNumberer(),
										withEmp_CK,
										 {header : "工号",width : 70,dataIndex : 'empCode'}, 
										 {header : "姓名",width : 70,dataIndex : 'empName'}, 
										 {header : "身份证号",width : 150,dataIndex : 'nationalIdentifier'},
										 {header : "所属部门",width : 160,dataIndex : 'deptName'},
										 {header : "岗位",width : 120,dataIndex : 'empDutyName'},
										 {header : "员工类型",width : 80,dataIndex : 'personType'},
										 {header : "职位属性",width : 80,dataIndex : 'empTypeName'}
										 ])
					});
		
			//有工号保存按钮
			var saveBtn2 = new Ext.Button({text:"保存",iconCls:'save',handler:withEmpAdd}) ;
			//员工查询按钮
			var empSearchBtn = new Ext.Button({text:"查询",iconCls:'search',handler:onEmpSearch}) ;
			
			function onEmpSearch(){
				withEmp_centerPanel.getStore().baseParams["selEmp.empCode"] = withEmp_queryForm.getForm().findField("selEmp.empCode").getValue() ;
				withEmp_centerPanel.getStore().baseParams["selEmp.empDutyName"] = withEmp_queryForm.getForm().findField("selEmp.empDutyName").getValue() ;
				withEmp_centerPanel.getStore().baseParams["selEmp.empName"] = withEmp_queryForm.getForm().findField("selEmp.empName").getValue() ;
				withEmp_centerPanel.getStore().baseParams["selEmp.innerFlg"] = true ;
				withEmp_centerPanel.getStore().baseParams["selEmp.onlyPublic"] = true ;
				
				withEmp_centerPanel.getStore().load();
			}
			function withEmpAdd(){
				var records = withEmp_centerPanel.getSelectionModel().getSelections();
				//判断是否选择数据
				if (records.length < 1) {
					Ext.MessageBox.alert('提示', '选择需要操作的数据');
					return ;
				} 
				//校验表单数据
				if (withEmp_addForm.getForm().isValid() == false) {
					Ext.MessageBox.alert("提示","数据校验错误！");
					return;
				}
				
				var ids = '';
				for(var i = 0; i < records.length; i++) {
					if(i==0){
						ids += records[i].data.id ;
					}else{
						ids += ',' + records[i].data.id ;
					}
				}
				
				withEmp_addForm.getForm().findField("ids").setValue(ids) ;
				//费用类型设置为公费
				withEmp_addForm.getForm().findField("insureEmp.feeType").setValue("1") ;
				
				withEmp_addForm.getForm().submit(
								{
									url : "insureEmp_saveBachWithEmpcode.action",
									success : showSaveOrUpdateSuccessInfo,
									failure : showSaveOrUpdateFailureInfo,
									waitMsg : "正在执行保存操作...",
									waitTitle : "请稍后..."
								});
			}
			
			
			/*********************************************************/
			
			var tabPain = new Ext.TabPanel({region:"center",activeItem:"noEmpTabId"
				,items: [
					new Ext.Panel({title:"有工号投保",layout:"form" ,id:"withEmpTabId"
						,tbar: [saveBtn2,empSearchBtn]
						,items: [ withEmp_addForm,withEmp_queryForm, withEmp_centerPanel ]
					})
					,new Ext.Panel({title:"无工号投保",layout:"border",id:"noEmpTabId"
						,tbar: [saveBtn1]
						,items: [editForm]
					})
				]
			});
			// 投保win
			var editWin = new Ext.Window({height:500,constrainHeader:true,width:950,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
					,items: [
						tabPain
					]
				});
			
			new Ext.Viewport({
				layout : 'border',
				items : [
						  {
							xtype:'SF.ims.treePanel',
							listeners : {
								click : function(node) {
									his_node = node;
									SF.deptCode = node.attributes.deptCode;
									SF.deptId = node.attributes.id;
									onSearch();
								}
							}				
						 },
						{
							region : 'center',
							tbar : [ '-'
							        <app:isPermission code="/insure/insureEmpSearch.action">
							        ,new Ext.Button({text:"查询",iconCls:'search',handler:onSearch })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureEmpDetail.action">
							        ,new Ext.Button({text:"查看",iconCls:'detail',handler:onDetail }) 
							        </app:isPermission>
							        <app:isPermission code="/insure/insureEmpAdd.action">
							        ,new Ext.Button({text:"投保",iconCls:'add',handler:onAdd })
							        </app:isPermission>
							          <app:isPermission code="/insure/insureEmpImportAdd.action">
							        ,new Ext.Button({text:"导入投保",iconCls:'add',handler:onImportAdd })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureEmpEdit.action">
							        ,new Ext.Button({text:"修改",iconCls:'edit',handler:onUpdate })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureEmpOtherEdit.action">
							        ,new Ext.Button({text:"其它变更",iconCls:'edit',handler:onCompanyUpdate })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureEmpDel.action">
							        ,new Ext.Button({text:"删除",iconCls:'delete',handler:onDelete })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureEmpEffect.action">
							        ,new Ext.Button({text:"生效",iconCls:'apply',handler:onSx })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureEmpImportEffect.action">
							        ,new Ext.Button({text:"导入生效",iconCls:'apply',handler:onDrsx })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureEmpEffectUpdate.action">
							        ,new Ext.Button({text:"生效变更",iconCls:'edit',handler:onUpdateSx })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureEmpReduce.action">
							        ,new Ext.Button({text:"减保",iconCls:'delete',handler:onJb })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureEmpExpire.action">
							        ,new Ext.Button({text:"失效",iconCls:'close',handler:onJbsx })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureEmpImportExpire.action">
							        ,new Ext.Button({text:"导入失效",iconCls:'close',handler:onDrjbsx })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureEmpExport.action">
							        ,new Ext.Button({text:"导出",iconCls:'revert',handler:onExport })
							        </app:isPermission>
							        
							         ],
							layout : 'border',

							items : [ queryForm, gridPanel ]
						} ],
						listeners : {
							afterrender : function(view){
								view.items.get(0).onInitHandler();
							}
						}
			});
			
			/*******************投保单位批量变更***********************/
			// 投保单位变更form属性
			var companyupdate_form_attr_1 = new Ext.form.TextField({width:150,name:"insureEmp.insureCompany",allowBlank:false,maxLength:100,fieldLabel:"投保单位",labelSeparator:'<font color=red>*</font>'});
			// 投保单位变更form
			var companyupdate_form = new Ext.form.FormPanel({region:"center",height:200,frame:true,fileUpload:false,autoWidth:true,timeout:300
							,items: [
								new Ext.Panel({layout:"column"
									,items: [
										new Ext.Panel({width:380,layout:"form"
											,items: [
											         companyupdate_form_attr_1
											]
										})
									]
								})
							]
						});
			// 投保单位变更窗口
			var companyupdate_win = new Ext.Window({height:200,constrainHeader:true,width:400,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
					,tbar: [
						new Ext.Button({text:"保存",iconCls:'save',handler:onCompanyUpdateSave})
					]
					,items: [
					         companyupdate_form
					]
				});
			//投保单位变更按钮事件
			function onCompanyUpdate(){
				var containSubdept = queryForm.getForm().findField('insureEmp.containSubdept').getValue() ;
				var msg = "" ;
				if(containSubdept == '1'){
					msg = "确定要变更【<font color='red'>"+his_node.text+"</font>】网点及子网点下所有记录的投保单位？" ;
				}else if(containSubdept == '0'){
					msg = "确定要变更【<font color='red'>"+his_node.text+"</font>】网点下所有记录的投保单位？" ;
				}
				Ext.MessageBox.confirm('提示',msg, companyUpdateSure);
			}
			function companyUpdateSure(result){
				if (result == 'yes') {
					companyupdate_win.setTitle("投保单位变更") ;
					companyupdate_win.show() ;
				}
			}
			//变更投保单位保存
			function onCompanyUpdateSave(){
				if (companyupdate_form.getForm().isValid() == false) {
					Ext.MessageBox.alert("提示","数据校验错误！");
					return  ;
				}
				var containSubdept = queryForm.getForm().findField('insureEmp.containSubdept').getValue() ;
				var insureCompany = companyupdate_form.getForm().findField('insureEmp.insureCompany').getValue() ;
				
				Ext.Ajax.request({params: {'insureEmp.containSubdept': containSubdept,'insureEmp.deptId':his_node.id,'insureEmp.insureCompany':insureCompany},
					url: "insureEmp_updateCompany.action",
					success: function(response) {
						var resp = Ext.util.JSON.decode(response.responseText);
						if (resp.success) {
							Ext.MessageBox.alert('提示','变更成功');
							companyupdate_win.hide() ;
							gridPanel.getStore().load();
						} else {
							Ext.MessageBox.alert('操作失败', resp.msg);
							companyupdate_win.hide() ;
						}
					},
					failure:ajaxRequestFailure
				});
			}

			/** *******************生效*********************** */
			// 生效
			var sx_form_attr_1 = new Ext.form.DateField({width:150,name:"insureEmp.effectTm",allowBlank:false,format:"Y-m-d",fieldLabel:"生效日期<font color=red>*</font>"});
			// 生效form
			var sx_form = new Ext.form.FormPanel({region:"center",height:200,frame:true,fileUpload:false,autoWidth:true,timeout:300
							,items: [
								new Ext.Panel({layout:"column"
									,items: [
										new Ext.Panel({width:380,layout:"form"
											,items: [
											         sx_form_attr_1
											]
										})
									]
								})
							]
						});
			// 生效窗口
			var sx_win = new Ext.Window({height:200,constrainHeader:true,width:400,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
					,tbar: [
						new Ext.Button({text:"保存",iconCls:'save',handler:onSxSave})
					]
					,items: [
					         sx_form
					]
				});
			// 生效按钮事件
			function onSx(){
				var records = gridPanel.getSelectionModel().getSelections();
				if (records.length < 1) {
					Ext.MessageBox.alert('提示', '选择需要操作的数据');
				} else {
					// 判断是否为在拟生效状态
					var records = gridPanel.getSelectionModel().getSelections();
					for(var i = 0; i < records.length; i++) {
						if(records[i].data.status == '2'){
							Ext.MessageBox.alert('提示', '数据已生效，无须再次生效');
							return ;
						}else if(records[i].data.status == '3'){
							Ext.MessageBox.alert('提示', '数据已减保，不能再进行生效操作');
							return ;
						}else if(records[i].data.status == '4'){
							Ext.MessageBox.alert('提示', '数据已失效，不能再进行生效操作');
							return ;
						}else if(records[i].data.status != '1'){
							Ext.MessageBox.alert('提示', '非拟生效状态的数据不可进行生效操作');
							return ;
						}
					}
					
					sx_win.setTitle("生效") ;
					sx_win.show() ;
					sx_form.getForm().reset();
				}
				
				
			}
			// 生效保存
			function onSxSave(){
				if (sx_form.getForm().isValid() == false) {
					Ext.MessageBox.alert("提示","数据校验错误！");
					return;
				}
				Ext.MessageBox.confirm('提示','确定要对选中的记录执行生效操作？', sx);
				
			}
			
			// 生效事件请求操作
			function sx(result) {
					if (result == 'yes') {
						var records = gridPanel.getSelectionModel().getSelections();
						
						// 1.若拟生效是追溯的，则在生效时，不要求生效日期大于拟生效日期。
						// 2.若拟生效是非追溯的，则在生效时，需要校验生效日期必须大于拟生效日期。 
						var effect_Tm = sx_form.getForm().findField('insureEmp.effectTm').getRawValue();
						for(var i = 0; i < records.length; i++) {
							if (records[i].data.review == '0' && effect_Tm <= records[i].data.planEffectTm ) {
								Ext.Msg.alert("提示", "生效日期必须大于所有选中非追溯记录的拟生效日期");
								return ;
							}
						}
						
						var ids = '';
						for(var i = 0; i < records.length; i++) {
							if(i==0){
								ids += records[i].data.insureId ;
							}else{
								ids += ',' + records[i].data.insureId ;
							}
						}
						
						var effectTm = sx_form.getForm().findField('insureEmp.effectTm').getValue();
						Ext.Ajax.request({params: {'ids': ids,'insureEmp.effectTm':effectTm},
							url: "insureEmp_effect.action",
							success: function(response) {
								var resp = Ext.util.JSON.decode(response.responseText);
								if (resp.success) {
									Ext.MessageBox.alert('提示','操作成功');
									sx_win.hide();
									gridPanel.getStore().reload();
								} else {
									Ext.MessageBox.alert('操作失败', resp.msg);
									sx_win.hide();
									gridPanel.getStore().reload();
								}
							},
							failure:ajaxRequestFailure
						});
					}
				}
			
			/** *******************减保*********************** */
			// 减保form属性
			var jb_form_attr_1 = new Ext.form.DateField({width:150,name:"insureEmp.planLoseTm",allowBlank:false,format:"Y-m-d",fieldLabel:"拟失效日期<font color=red>*</font>"});
			
			// 减保form
			var jb_form = new Ext.form.FormPanel({region:"center",height:200,frame:true,fileUpload:false,autoWidth:true,timeout:300
							,items: [
								new Ext.Panel({layout:"column"
									,items: [
										new Ext.Panel({width:380,layout:"form"
											,items: [
											         jb_form_attr_1
											]
										})
									]
								})
							]
						});
			// 减保窗口
			var jb_win = new Ext.Window({height:200,constrainHeader:true,width:400,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
					,tbar: [
					new Ext.Button({text:"保存",iconCls:'save',handler:onJbSave})
				]
				,items: [
				         jb_form
				]
			});
			// 减保按钮事件
			function onJb(){
				//TODO
				var records = gridPanel.getSelectionModel().getSelections();
				if (records.length < 1) {
					Ext.MessageBox.alert('提示', '选择需要操作的数据');
				} else {
					// 判断是否为生效状态
					var records = gridPanel.getSelectionModel().getSelections();
					for(var i = 0; i < records.length; i++) {
						if(records[i].data.status == '1'){
							Ext.MessageBox.alert('提示', '数据未生效，无法减保');
							return ;
						}else if(records[i].data.status == '3'){
							Ext.MessageBox.alert('提示', '数据已减保，无须再次减保');
							return ;
						}else if(records[i].data.status == '4'){
							Ext.MessageBox.alert('提示', '数据已失效，不能进行减保操作');
							return ;
						}else if(records[i].data.status != '2'){
							Ext.MessageBox.alert('提示', '非生效状态的数据不可进行减保操作');
							return ;
						}
						if(records[i].data.feeType == '2'){
							Ext.MessageBox.alert('提示', '自费员工不能减保');
							return ;
						}
					}
					
					jb_win.setTitle("减保") ;
					jb_win.show() ;
					jb_form.getForm().reset();
				}
				
				
			}
			// 减保保存
			function onJbSave(){
				if (jb_form.getForm().isValid() == false) {
					Ext.MessageBox.alert("提示","数据校验错误！");
					return;
				}
				
				Ext.MessageBox.confirm('提示','确定要对选中的记录执行减保操作？', jb);
				
			}
			
			// 减保事件请求操作
			function jb(result) {
					if (result == 'yes') {
						var records = gridPanel.getSelectionModel().getSelections();
						// 判断拟失效时间是否大于所有选择的记录的生效时间
						var planLoseTm = jb_form.getForm().findField('insureEmp.planLoseTm').getRawValue();
						for(var i = 0; i < records.length; i++) {
							var effectTm = records[i].data.effectTm ;
							if (planLoseTm <effectTm.split('T')[0] ) {
								Ext.Msg.alert("提示", "拟失效日期必须大于所有选中记录的生效日期");
								return ;
							}
						}
						
						var ids = '';
						for(var i = 0; i < records.length; i++) {
							if(i==0){
								ids += records[i].data.insureId ;
							}else{
								ids += ',' + records[i].data.insureId ;
							}
						}
						
						Ext.Ajax.request({params: {'ids': ids,'insureEmp.planLoseTm':planLoseTm},
							url: "insureEmp_reduceInsure.action",
							success: function(response) {
								var resp = Ext.util.JSON.decode(response.responseText);
								if (resp.success) {
									Ext.MessageBox.alert('提示','操作成功');
									jb_win.hide();
									gridPanel.getStore().reload();
								} else {
									Ext.MessageBox.alert('操作失败', resp.msg);
									jb_win.hide();
									gridPanel.getStore().reload();
								}
							},
							failure:ajaxRequestFailure
						});
					}
				}
			/** *******************失效*********************** */
			// 失效form属性
			var jbsx_form_attr_1 = new Ext.form.DateField({width:150,name:"insureEmp.loseTm",allowBlank:false,format:"Y-m-d",fieldLabel:"失效日期<font color=red>*</font>"});

			// 失效form
			var jbsx_form = new Ext.form.FormPanel({region:"center",height:200,frame:true,fileUpload:false,autoWidth:true,timeout:300
							,items: [
								new Ext.Panel({layout:"column"
									,items: [
										new Ext.Panel({width:380,layout:"form"
											,items: [
											         jbsx_form_attr_1
											]
										})
									]
								})
							]
						});
			// 失效窗口
			var jbsx_win = new Ext.Window({height:200,constrainHeader:true,width:400,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
					,tbar: [
					new Ext.Button({text:"保存",iconCls:'save',handler:onJbsxSave})
				]
				,items: [
				         jbsx_form
				]
			});
			// 失效按钮事件
			function onJbsx(){
				var records = gridPanel.getSelectionModel().getSelections();
				if (records.length < 1) {
					Ext.MessageBox.alert('提示', '选择需要操作的数据');
				} else {
					// 判断是否为在拟失效状态
					var records = gridPanel.getSelectionModel().getSelections();
					for(var i = 0; i < records.length; i++) {
						if(records[i].data.status == '1'){
							Ext.MessageBox.alert('提示', '数据未生效，无法失效');
							return ;
						}else if(records[i].data.status == '2'){
							Ext.MessageBox.alert('提示', '数据未减保，不能进行失效操作');
							return ;
						}else if(records[i].data.status == '4'){
							Ext.MessageBox.alert('提示', '数据已失效，无须再次失效');
							return ;
						}else if(records[i].data.status != '3'){
							Ext.MessageBox.alert('提示', '非拟失效状态的数据不可进行失效操作');
							return ;
						}
					}
					
					jbsx_win.setTitle("失效") ;
					jbsx_win.show() ;
					jbsx_form.getForm().reset();
				}
				
				
			}
			// 失效保存
			function onJbsxSave(){
				if (jbsx_form.getForm().isValid() == false) {
					Ext.MessageBox.alert("提示","数据校验错误！");
					return;
				}
				Ext.MessageBox.confirm('提示','确定要对选中的记录执行失效操作？', jbsx);
				
			}
			
			// 失效事件请求操作
			function jbsx(result) {
					if (result == 'yes') {
						var records = gridPanel.getSelectionModel().getSelections();
						var loseTm = jbsx_form.getForm().findField('insureEmp.loseTm').getRawValue();
						for(var i = 0; i < records.length; i++) {
							var effectTm = records[i].data.effectTm ;
							if (loseTm <effectTm.split('T')[0] ) {
								Ext.Msg.alert("提示", "失效日期必须大于所有选中记录的生效日期");
								return ;
							}
						}
						
						var ids = '';
						for(var i = 0; i < records.length; i++) {
							if(i==0){
								ids += records[i].data.insureId ;
							}else{
								ids += ',' + records[i].data.insureId ;
							}
						}
						
						
						Ext.Ajax.request({params: {'ids': ids,'insureEmp.loseTm':loseTm},
							url: "insureEmp_unEffect.action",
							success: function(response) {
								var resp = Ext.util.JSON.decode(response.responseText);
								if (resp.success) {
									Ext.MessageBox.alert('提示','操作成功');
									jbsx_win.hide();
									gridPanel.getStore().reload();
								} else {
									Ext.MessageBox.alert('操作失败', resp.msg);
									jbsx_win.hide();
									gridPanel.getStore().reload();
								}
							},
							failure:ajaxRequestFailure
						});
					}
				}
			
			
			/** **********************生效变更*************************** */
			
			// 生效变更按钮事件
			function onUpdateSx(){
				var records = gridPanel.getSelectionModel().getSelections();
				var record = gridPanel.getSelectionModel().getSelected();
				if (records.length == 1) {
					// 判断状态是否为生效状态
					var status = record.get("status");
					if(status != '2'){
						Ext.MessageBox.alert('提示', '非生效状态的数据不可进行修改操作');
						return ;
					}
					operator_type = 'updateEffect';
					all_editWin.setTitle("生效变更");
					all_editWin.show();
					
					var obj = {};
					for (p in record.data) {
						if (p == 'divisionTm' || p == 'planEffectTm' || p == 'effectTm' || p == 'borthday' ) {
							if(record.data[p] != null){
								all_editForm.getForm().findField('insureEmp.' + p).setValue(record.data[p].split('T')[0]);
							}else{
								all_editForm.getForm().findField('insureEmp.' + p).setValue('');
							}
							
							continue;
						}
						obj['insureEmp.' + p] = record.data[p]; 
					}
					all_editForm.getForm().setValues(obj);
					
					preUpdateSxbg() ;
					
				}else if (records.length < 1) {
					Ext.Msg.alert("提示","请选择一笔数据行");
				} else if (records.length > 1) {
					Ext.Msg.alert("提示","不能同时操作多条数据");
				}
			}
		

	/** ****************导入生效、失效、投保*************************** */
			
	// 导入form
	var importForm = new Ext.form.FormPanel({
		region : "center",
		frame : true,
		fileUpload : true,
		method : "POST",
		items : [{
			layout : "column",
			items : [{
						columnWidth : 0.7,
						layout : 'form',
						labelWidth : 60,
						items : {
							xtype : 'textfield',
							inputType : "file",
							name : "uploadFile",
							fieldLabel : "选择文件"
						}
					}, {
						columnWidth : 0.3,
						items : {
							html : "&nbsp;&nbsp;<a style=\"text-decoration:none;color:#11439C;line-height:23px;font-size:12;padding-top:2px;height:25px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;\"  onmouseover=\"this.style.color=\'red\';this.style.cursor=\'hand\'\" onmouseout=\"this.style.color=\'#11439C\'\" href=\'#\' onClick=\"window.location ='insureEmp_downloadTpl.action';\"><img src=\"../images/insure/download.gif\" /><span>导入模板.xls</span></a>"
						}
						
					}, {
						columnWidth : 1,
						items : {
							frame : true,
							html : "<span>请先下载模板，根据模板填写相关信息。填写完成后请上传该文件。 （注：本系统支持OFFICE2003,只能上传2M以内的文件，上传数据量小于1000条）。</span>"
						}
					}, {
						hidden:true,
						items : {
							html : "<iframe style=\"display: none\" id=\"drsx_batch_hidden_frame\" name=\'drsx_batch_hidden_frame\'></iframe>"
						}
					}

			]
		}]
	});
	
	// 导入窗口
	var importWin = new Ext.Window({
		autoHeight:true,
		constrainHeader : true,
		width : 500,
		closeAction : "hide",
		resizable : false,
		isEditing : false,
		plain : true,
		modal : true,
		listeners:{
			 hide:function(){
			 	importForm.getForm().reset();
			 }
			},
		tbar : [new Ext.Button({
					icon : "../images/save.gif",
					text : "保存",
					cls : "x-btn-text-icon",
					handler : function() {
						// TODO 返回带<pre>原因？
						importForm.getForm().submit({
							url : importWin.url,
							fileUpload : true,
							success : function(form, action) {
								Ext.MessageBox.alert("提示","导入成功");
								importWin.hide();
								gridStore.reload();
							},
							failure : function(form, action) {
								var o = Ext.decode(action.response.responseText
										.replace(/<pre>/, '').replace(
												/<\/pre>/, ''));
								if (o.success) {
									importWin.hide();
									gridStore.reload();
									return;
								}
								Ext.MessageBox.showErr(o.msg);
							},
							waitMsg : "导入中..."
						});
					}
				})],
		items :  importForm
	});
		
			// 导入生效
			function onDrsx(){
				importWin.setTitle("导入生效");
				importWin.url = 'insureEmp_importEffect.action';
				importWin.show() ;
			}
		
			// 导入失效
			function onDrjbsx(){
				importWin.setTitle("导入失效");
				importWin.url = 'insureEmp_importUneffect.action';
				importWin.show() ;
			}
			
			
			// 导入投保
			function onImportAdd(){
				importWin.setTitle("导入投保");
				importWin.url = 'insureEmp_importAdd.action';
				importWin.show() ;
			}
			
			
			// 查询
			function onSearch() {

				queryForm.getForm().findField('insureEmp.deptId').setValue(
						his_node.id);

				if (queryForm.getForm().isValid() == false) {
					Ext.MessageBox.alert("提示","数据校验错误！");
				} else {
					// 判断拟生效日期
					var planEffectTmBegin = queryForm.getForm().findField('insureEmp.planEffectTmBegin').getValue();
					var planEffectTmEnd = queryForm.getForm().findField('insureEmp.planEffectTmEnd').getValue();
					if (planEffectTmBegin != '' && planEffectTmEnd != '') {
						if (planEffectTmBegin > planEffectTmEnd) {
							Ext.Msg.alert("提示", "拟生效开始日期不能大于结束日期");
							return ;
						}
					}
					// 判断生效日期
					var effectTmBegin = queryForm.getForm().findField('insureEmp.effectTmBegin').getValue();
					var effectTmEnd = queryForm.getForm().findField('insureEmp.effectTmEnd').getValue();
					if (effectTmBegin != '' && effectTmEnd != '') {
						if (effectTmBegin > effectTmEnd) {
							Ext.Msg.alert("提示", "生效开始日期不能大于结束日期");
							return ;
						}
					}
					// 判断拟失效日期
					var planLoseTmBegin = queryForm.getForm().findField('insureEmp.planLoseTmBegin').getValue();
					var planLoseTmEnd = queryForm.getForm().findField('insureEmp.planLoseTmEnd').getValue();
					if (planLoseTmBegin != '' && planLoseTmEnd != '') {
						if (planLoseTmBegin > planLoseTmEnd) {
							Ext.Msg.alert("提示", "拟失效开始日期不能大于结束日期");
							return ;
						}
					}
					// 判断失效日期
					var loseTmBegin = queryForm.getForm().findField('insureEmp.loseTmBegin').getValue();
					var loseTmEnd = queryForm.getForm().findField('insureEmp.loseTmEnd').getValue();
					if (loseTmBegin != '' && loseTmEnd != '') {
						if (loseTmBegin > loseTmEnd) {
							Ext.Msg.alert("提示", "失效开始日期不能大于结束日期");
							return ;
						}
					}
					
					gridPanel.getStore().baseParams = queryForm.getForm().getValues();
					gridPanel.getStore().baseParams["limit"] = pagingBar.pageSize;
					gridPanel.getStore().load();
				}
			}
			// 添加
			function onAdd() {
				
				operator_type = 'add';
				editWin.setTitle("投保");
				editWin.show();
				editForm.getForm().reset();
				withEmp_addForm.getForm().reset();
				withEmp_queryForm.getForm().reset();
				withEmp_centerPanel.getStore().removeAll(false);
				
				preAdd() ;
				
			}
		
			// 修改
			function onUpdate() {
				
				operator_type = 'update';
				var records = gridPanel.getSelectionModel().getSelections();
				var record = gridPanel.getSelectionModel().getSelected();
				if (records.length == 1) {
					// 判断状态是否为拟生效状态
					var status = record.get("status");
					if(status != '1'){
						Ext.MessageBox.alert("提示","非拟生效状态的记录不容许进行修改");
						return ;
					}
					
					all_editWin.setTitle("修改");
					all_editWin.show();
					var obj = {};
					for (p in record.data) {
						if (p == 'divisionTm' || p == 'planEffectTm' || p == 'effectTm' || p == 'borthday' ) {
							if(record.data[p] != null){
								all_editForm.getForm().findField('insureEmp.' + p).setValue(record.data[p].split('T')[0]);
							}else{
								all_editForm.getForm().findField('insureEmp.' + p).setValue('');
							}
							
							continue;
						}
						obj['insureEmp.' + p] = record.data[p]; 
					}
					all_editForm.getForm().setValues(obj);
					// 判断是有工号修改还是无工号修改
					var empCode = record.get("empCode");
					if(empCode != '' && empCode != null){// 有工号
						preUpdateHasEmpCode() ;
					}else{// 无工号
						preUpdateNoEmpCode() ;
					}
					
					
				} else if (records.length < 1) {
					Ext.Msg.alert("提示","请选择一笔数据行");
				} else if (records.length > 1) {
					Ext.Msg.alert("提示","不能同时修改多条数据");
				}

			}
			
			// 投保为草稿或修改
			function onSaveOrUpdate() {
				if (operator_type == 'add') {
					if (editForm.getForm().isValid() == false) {
						Ext.MessageBox.alert("提示","数据校验错误！");
						return;
					}
				} else if (operator_type == 'update' || operator_type == 'updateEffect') {
					if (all_editForm.getForm().isValid() == false) {
						Ext.MessageBox.alert("提示","数据校验错误！");
						return;
					}
				}
				
				// 检验身份证号码格式
				/*var idcard ;
				if (operator_type == 'add') {
					idcard = editForm.getForm().findField("insureEmp.idcard").getValue() ;
				} else if (operator_type == 'update' || operator_type == 'updateEffect') {
					idcard = all_editForm.getForm().findField("insureEmp.idcard").getValue() ;
				}
				if(!checkIdcardFormate(idcard)){
					Ext.MessageBox.alert("提示","身份证格式错误");
					return  ;
				}*/
				
				var waitMsg;
				if (operator_type == 'add') {
					waitMsg = "正在执行保存操作...";
					editForm.getForm().url = "insureEmp_save.action";
				} else if (operator_type == 'update') {
					waitMsg = "正在执行修改操作...";
					all_editForm.getForm().url = "insureEmp_update.action";
				}else if (operator_type == 'updateEffect') {
					waitMsg = "正在执行修改操作...";
					all_editForm.getForm().url = "insureEmp_updateEffect.action";
				}
				
				if (operator_type == 'add') {
					editForm.getForm().submit(
							{
								success : showSaveOrUpdateSuccessInfo,
								failure : showSaveOrUpdateFailureInfo,
								waitMsg : waitMsg,
								waitTitle : "请稍后..."
							});
				} else if (operator_type == 'update' || operator_type == 'updateEffect') {
					all_editForm.getForm().submit(
							{
								success : showSaveOrUpdateSuccessInfo,
								failure : showSaveOrUpdateFailureInfo,
								waitMsg : waitMsg,
								waitTitle : "请稍后..."
							});
				}
				
				
			}
			
			function checkIdcardFormate(idcard) {
				  // 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
				   var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
				   if(reg.test(idcard) === false){  
				      return  false;    
				      }  
				   return true ;
			}
			
			function showSaveOrUpdateSuccessInfo(form, action) {
				if (action.result.msg) {
					Ext.Msg.alert("提示",
							action.result.msg);
				} else {
					if (operator_type == 'add') {
						Ext.Msg.alert("提示","保存成功");
						editWin.hide();
					} else if (operator_type == 'update') {
						Ext.Msg.alert("提示","更新成功");
						all_editWin.hide();
					}else if (operator_type == 'updateEffect') {
						Ext.Msg.alert("提示","更新成功");
						all_editWin.hide();
					}
					
					gridStore.reload();
				}
			}
			

			function showSaveOrUpdateFailureInfo(form, action) {
				var msg = '';
				if (action && action.result && action.result.error) {
					Ext.Msg.alert("提示","系统出现了异常,请与管理员联系!");
				} else {
					if (action.failureType === Ext.form.Action.CONNECT_FAILURE) {
						if (action.response.status == 0) {
							msg = "连接失败,无法连接到服务器!";
						} else if (action.response.status == -1) {
							msg = "服务器处理超时!";
						} else {
							msg = String.format("错误代码:{0}, 错误描述:{1}!",action.response.status,action.response.statusText);
						}
					} else if (action.failureType === Ext.form.Action.CLIENT_INVALID) {
						msg = "请将表单填写正确!";
					}
					Ext.Msg.alert("提示", msg);
				}
			}
			
			// 导出
			function onExport() {
				
				//导出前先进行规则验证
				Ext.Ajax.request({
					params:  queryForm.getForm().getValues(true) ,
					method :"POST",
					url: "insureEmp_exportValidate.action",
					success: function(response) {
						var resp = Ext.util.JSON.decode(response.responseText);
						if (resp.success) {
							queryForm.getForm().doAction('submit', {
								url : "exportInsureEmps.action",
								success : showExportResult,
								failure : showSaveOrUpdateFailureInfo
							});
						} else {
							Ext.MessageBox.alert('提示', resp.msg);
						}
					},
					failure:ajaxRequestFailure
				});
				
			}
			

			//导出成功处理事件
			  function showExportResult(form, action) {
				}
			
			
			// 删除事件
			function onDelete() {
					
					var records = gridPanel.getSelectionModel().getSelections();
					if (records.length < 1) {
						Ext.MessageBox.alert('提示', '选择需要删除的数据');
					} else {
						// 判断状态是否为拟生效
						var records = gridPanel.getSelectionModel().getSelections();
						for(var i = 0; i < records.length; i++) {
							if(records[i].data.status != '1'){
								Ext.MessageBox.alert('提示', '非拟生效状态的数据不可删除');
								return ;
							}
						}
						
						Ext.MessageBox.confirm('提示','确定要删除选中的记录吗', deleteRecord);
					}
				}
				
				// 删除事件请求调用
			function deleteRecord(result) {
					if (result == 'yes') {
						var records = gridPanel.getSelectionModel().getSelections();
						var ids = '';
						for(var i = 0; i < records.length; i++) {
							if(i==0){
								ids += records[i].data.insureId ;
							}else{
								ids += ',' + records[i].data.insureId ;
							}
						}
						Ext.Ajax.request({params: {ids: ids},
							url: "insureEmp_delete.action",
							success: function(response) {
								var resp = Ext.util.JSON.decode(response.responseText);
								if (resp.success) {
									Ext.MessageBox.alert('提示','数据删除成功');
									gridPanel.getStore().load();
								} else {
									Ext.MessageBox.alert('操作失败', resp.msg);
									gridPanel.getStore().load();
								}
							},
							failure:ajaxRequestFailure
						});
					}
				}
			
			
			// ajax请求失败处理事件
			function ajaxRequestFailure(response) {
				  if (response.status == 0) {
				      Ext.Msg.alert("提示", "无法连接到服务器，请检查网络是否正常");
				  } else if (response.status == -1) {
				      Ext.Msg.alert("提示", "服务器处理超时，请稍后再试");
				  } else {
						Ext.Msg.alert("提示","系统出现异常,请与管理员联系");
					}
				}
			/*************************修改***************************************/
			var all_editForm_atrr_1 = new Ext.form.TextField({width:150,name:"insureEmp.insureNo",readOnly:true,allowBlank:true,fieldLabel:"投保编号"});

			var all_editForm_atrr_2 = new Ext.form.TextField({width:150,name:"insureEmp.insureCompany",allowBlank:false,maxLength:100,fieldLabel:"投保单位",labelSeparator:'<font color=red>*</font>'});
			
			var all_editForm_atrr_3 = new Ext.form.TextField({width:150,name:"insureEmp.empName",allowBlank:false,maxLength:100,fieldLabel:"姓名",labelSeparator:'<font color=red>*</font>'});
			
			var all_editForm_atrr_4 = new Ext.form.ComboBox({hiddenName:"insureEmp.gender",allowBlank:false,store:new Ext.data.SimpleStore({data: [['男','男'],['女','女']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureEmp.gender",triggerAction:"all",fieldLabel:"性别",editable:false,mode:"local",labelSeparator:'<font color=red>*</font>'});

			var all_editForm_atrr_5 = new Ext.form.DateField({width:150,name:"insureEmp.borthday",allowBlank:false,format:"Y-m-d",fieldLabel:"出生日期",labelSeparator:'<font color=red>*</font>'});

			var all_editForm_atrr_6 = new Ext.form.TextField({width:150,name:"insureEmp.idcard",allowBlank:false,minLength:15,maxLength:18,fieldLabel:"身份证号码",validator:function(value){
				var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
				   if(reg.test(value) === true){  
				      return  true;    
				      }  
				   return "身份证格式错误,长度为15或18，例如432524198811228或432524198811228888或43252419881122888X" ;
			},labelSeparator:'<font color=red>*</font>'});
			
			var all_editForm_atrr_7 = new Ext.form.DateField({width:150,name:"insureEmp.divisionTm",allowBlank:false,format:"Y-m-d",fieldLabel:"入司时间",labelSeparator:'<font color=red>*</font>'});

			var all_editForm_atrr_8 = new Ext.form.ComboBox({hiddenName:"insureEmp.persionType",allowBlank:false,store:new Ext.data.SimpleStore({data: [['全日制员工','全日制员工'],['劳务派遣','劳务派遣'],['非全日制','非全日制'],['实习生','实习生'],['勤工助学','勤工助学'],['基地见习生','基地见习生'],['其他','其他']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureEmp.persionType",triggerAction:"all",fieldLabel:"人员类型",editable:false,mode:"local",labelSeparator:'<font color=red>*</font>'});

			var all_editForm_atrr_9 = new Ext.form.TextField({width:100,name:"insureEmp.deptName",allowBlank:false,fieldLabel:"所属部门",labelSeparator:'<font color=red>*</font>'});

			var all_editForm_atrr_10 = new Ext.form.TextField({width:150,name:"insureEmp.dutyName",allowBlank:false,fieldLabel:"职位名称",maxLength:100,labelSeparator:'<font color=red>*</font>'});

			var all_editForm_atrr_11 = new Ext.form.ComboBox({hiddenName:"insureEmp.dutyType",allowBlank:false,store:new Ext.data.SimpleStore({data: [['一线','一线'],['二线','二线'],['三线','三线']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureEmp.dutyType",triggerAction:"all",fieldLabel:"职位属性",editable:false,mode:"local",labelSeparator:'<font color=red>*</font>'});

			var all_editForm_atrr_12 = new Ext.form.DateField({width:150,name:"insureEmp.planEffectTm",allowBlank:false,format:"Y-m-d",fieldLabel:"拟生效日期",labelSeparator:'<font color=red>*</font>'});

			var all_editForm_atrr_13 = new Ext.form.DateField({width:150,name:"insureEmp.effectTm",format:"Y-m-d",fieldLabel:"生效日期"});

			var all_editForm_atrr_14 = new Ext.form.ComboBox({hiddenName:"insureEmp.review",allowBlank:false,store:new Ext.data.SimpleStore({data: [['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureEmp.review",value:"0",triggerAction:"all",fieldLabel:"是否追溯",editable:false,mode:"local",labelSeparator:'<font color=red>*</font>'});
			
			var all_editForm_atrr_15 = new Ext.form.ComboBox({hiddenName:"insureEmp.feeType",allowBlank:false,store:new Ext.data.SimpleStore({data: [['1','公费'],['2','自费']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureEmp.feeType",value:"1",triggerAction:"all",fieldLabel:"费用类型",editable:false,mode:"local",labelSeparator:'<font color=red>*</font>'});

			var all_editForm_atrr_16 = new Ext.form.TextArea({height:150,width:750,name:"insureEmp.descipt",allowBlank:true,maxLength:1300,fieldLabel:"备注"});
			
			var all_editForm_atrr_17 = new Ext.form.Hidden({name:"insureEmp.insureId"});
			
			var all_editForm_atrr_18 = new Ext.form.Hidden({name:"insureEmp.deptId"});
			
			var all_editForm_atrr_19 = new Ext.form.Hidden({name:"insureEmp.version"});
			
			var all_selectDeptBtn = new Ext.Button({text:"部门",iconCls:'revert',handler:onSelectDept}) ;
			
			var all_editForm = new Ext.form.FormPanel({region:"center",height:200,frame:true,fileUpload:false,autoWidth:true,timeout:300
				,items: [
					new Ext.Panel({layout:"column",
						items: [
							new Ext.Panel({width:300,layout:"form",items: [all_editForm_atrr_1]})
							,new Ext.Panel({width:300,layout:"form",items: [all_editForm_atrr_2]})
							,new Ext.Panel({width:300,layout:"form",items: [all_editForm_atrr_3]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [	all_editForm_atrr_4]})
							,new Ext.Panel({width:300,layout:"form",items: [all_editForm_atrr_5]})
							,new Ext.Panel({width:300,layout:"form",items: [all_editForm_atrr_6]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [	all_editForm_atrr_7]})
							,new Ext.Panel({width:300,layout:"form",items: [all_editForm_atrr_8]})
							,new Ext.Panel({width:210,layout:"form",items: [all_editForm_atrr_9]})
							,new Ext.Panel({width:90,layout:"form",items: [	all_selectDeptBtn]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [	all_editForm_atrr_10]})
							,new Ext.Panel({width:300,layout:"form",items: [all_editForm_atrr_11]})
							,new Ext.Panel({width:300,layout:"form",items: [all_editForm_atrr_12]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [	all_editForm_atrr_13]})
							,new Ext.Panel({width:300,layout:"form",items: [all_editForm_atrr_14]})
							,new Ext.Panel({width:300,layout:"form",items: [all_editForm_atrr_15]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:950,layout:"form",items: [all_editForm_atrr_16,all_editForm_atrr_17,all_editForm_atrr_18,all_editForm_atrr_19]})
							
						]
					})
				]

			});
			//修改保存按钮
			var updatesaveBtn = new Ext.Button({text:"保存",iconCls:'save',handler:onSaveOrUpdate}) ;
			// 修改win
			var all_editWin = new Ext.Window({height:400,constrainHeader:true,width:950,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
				,tbar: [
				        updatesaveBtn
				]
				,items: [
				         all_editForm
				]
			});
			
			
			/*************************查看详情***************************************/
			// 详情的表单属性
			var detailForm_atrr_1 = new Ext.form.TextField({width:150,name:"insureEmp.insureNo",readOnly:true,fieldLabel:"投保编号"});
			
			var detailForm_atrr_2 = new Ext.form.ComboBox({hiddenName:"insureEmp.status",readOnly:true,store:new Ext.data.SimpleStore({data: [['1','拟生效'],['2','生效'],['3','拟失效'],['4','失效']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureEmp.review",triggerAction:"all",fieldLabel:"状态",editable:false,mode:"local"});

			var detailForm_atrr_3 = new Ext.form.TextField({width:150,name:"insureEmp.areaName",readOnly:true,fieldLabel:"所属地区"});

			var detailForm_atrr_4 = new Ext.form.TextField({width:150,name:"insureEmp.insureCompany",readOnly:true,fieldLabel:"投保单位"});
			
			var detailForm_atrr_5 = new Ext.form.TextField({width:150,name:"insureEmp.deptName",readOnly:true,fieldLabel:"所属部门"});
			
			var detailForm_atrr_6 = new Ext.form.TextField({width:150,name:"insureEmp.empCode",readOnly:true,fieldLabel:"工号"});
			
			var detailForm_atrr_7 = new Ext.form.TextField({width:150,name:"insureEmp.empName",readOnly:true,fieldLabel:"姓名"});

			var detailForm_atrr_8 = new Ext.form.TextField({width:150,name:"insureEmp.idcard",readOnly:true,fieldLabel:"身份证号码"});
			
			var detailForm_atrr_9 = new Ext.form.ComboBox({hiddenName:"insureEmp.gender",readOnly:true,store:new Ext.data.SimpleStore({data: [['男','男'],['女','女']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureEmp.gender",value:"男",triggerAction:"all",fieldLabel:"性别",editable:false,mode:"local"});

			var detailForm_atrr_10 = new Ext.form.DateField({width:150,name:"insureEmp.borthday",readOnly:true,format:"Y-m-d",fieldLabel:"出生日期"});

			var detailForm_atrr_11 = new Ext.form.DateField({width:150,name:"insureEmp.divisionTm",readOnly:true,format:"Y-m-d",fieldLabel:"入司时间"});

			var detailForm_atrr_12 = new Ext.form.TextField({width:150,name:"insureEmp.persionType",readOnly:true,fieldLabel:"人员类型"});

			var detailForm_atrr_13 = new Ext.form.TextField({width:150,name:"insureEmp.dutyName",readOnly:true,fieldLabel:"职位名称"});

			var detailForm_atrr_14 = new Ext.form.TextField({width:150,name:"insureEmp.dutyType",readOnly:true,fieldLabel:"职位属性"});

			var detailForm_atrr_15 = new Ext.form.ComboBox({hiddenName:"insureEmp.review",readOnly:true,store:new Ext.data.SimpleStore({data: [['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureEmp.review",triggerAction:"all",fieldLabel:"是否追溯",editable:false,mode:"local"});

			var detailForm_atrr_16 = new Ext.form.DateField({width:150,name:"insureEmp.planEffectTm",readOnly:true,format:"Y-m-d",fieldLabel:"拟生效日期"});

			var detailForm_atrr_17 = new Ext.form.DateField({width:150,name:"insureEmp.effectTm",readOnly:true,format:"Y-m-d",fieldLabel:"生效日期"});
			
			var detailForm_atrr_18 = new Ext.form.DateField({width:150,name:"insureEmp.planLoseTm",readOnly:true,format:"Y-m-d",fieldLabel:"拟失效日期"});

			var detailForm_atrr_19 = new Ext.form.DateField({width:150,name:"insureEmp.loseTm",readOnly:true,format:"Y-m-d",fieldLabel:"失效日期"});
			
			var detailForm_atrr_20 = new Ext.form.TextField({width:150,name:"insureEmp.days",readOnly:true,fieldLabel:"在保天数"});

			var detailForm_atrr_21 = new Ext.form.TextField({width:150,name:"insureEmp.totalInsureFee",readOnly:true,fieldLabel:"保费(元)"});

			var detailForm_atrr_22 = new Ext.form.ComboBox({hiddenName:"insureEmp.feeType",readOnly:true,store:new Ext.data.SimpleStore({data: [['1','公费'],['2','自费']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureEmp.feeType",triggerAction:"all",fieldLabel:"费用类型",editable:false,mode:"local"});

			var detailForm_atrr_23 = new Ext.form.TextField({width:150,name:"insureEmp.createEmpName",readOnly:true,fieldLabel:"创建人"});
			
			var detailForm_atrr_24 = new Ext.form.TextField({width:150,name:"insureEmp.createTm",readOnly:true,fieldLabel:"创建时间"});
			
			var detailForm_atrr_25 = new Ext.form.TextField({width:150,name:"insureEmp.updateEmpName",readOnly:true,fieldLabel:"修改人"});
			
			var detailForm_atrr_26 = new Ext.form.TextField({width:150,name:"insureEmp.updateTm",readOnly:true,fieldLabel:"修改时间"});

			var detailForm_atrr_27 = new Ext.form.TextArea({height:150,width:750,name:"insureEmp.descipt",readOnly:true,fieldLabel:"备注"});
			

			var detailForm = new Ext.form.FormPanel({region:"center",height:200,frame:true,fileUpload:false,autoWidth:true,timeout:300
				,items: [
					new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_1]})
							,new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_2]})
							,new Ext.Panel({width:300,layout:"form"	,items: [detailForm_atrr_3]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [	detailForm_atrr_4]})
							,new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_5]})
							,new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_6]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [	detailForm_atrr_7]})
							,new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_8]})
							,new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_9]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [	detailForm_atrr_10]})
							,new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_11]})
							,new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_12]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [	detailForm_atrr_13]})
							,new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_14]})
							,new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_15]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [	detailForm_atrr_16]})
							,new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_17]})
							,new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_18]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [	detailForm_atrr_19]})
							,new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_20]})
							,new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_21]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_22]})
							,new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_23]})
							,new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_24]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_25]})
							,new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_26]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:950,layout:"form",items: [detailForm_atrr_27]})
							
						]
					})
				]

			});
			
			// 详情form
			var detailWin = new Ext.Window({height:440,constrainHeader:true,width:950,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
				,items: [
				         detailForm
				]
			});
			
			function onDetail() {
				
				operator_type = 'detail';
				var records = gridPanel.getSelectionModel().getSelections();
				var record = gridPanel.getSelectionModel().getSelected();
				if (records.length == 1) {
					detailWin.setTitle("查看明细");
					detailWin.show();
					var obj = {};
					for (p in record.data) {
						if (p == 'divisionTm' || p == 'planEffectTm' || p == 'effectTm' || p == 'borthday' || p == 'planLoseTm' || p == 'loseTm') {
							if(record.data[p] != null){
								detailForm.getForm().findField('insureEmp.' + p).setValue(record.data[p].split('T')[0]);
							}else{
								detailForm.getForm().findField('insureEmp.' + p).setValue('');
							}
							continue;
						}
						if (p == 'createTm' || p == 'updateTm') {
							if(record.data[p] != null){
								detailForm.getForm().findField('insureEmp.' + p).setValue(record.data[p].replace('T',' '));
							}else{
								detailForm.getForm().findField('insureEmp.' + p).setValue('');
							}
							continue;
						}
						obj['insureEmp.' + p] = record.data[p]; 
					}
					detailForm.getForm().setValues(obj);
					
				} else if (records.length < 1) {
					Ext.Msg.alert("提示","请选择一条记录");
				} else if (records.length > 1) {
					Ext.Msg.alert("提示","不能同时操作多条记录");
				}
			}
			
		});