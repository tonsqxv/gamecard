<%@ page language="java" contentType="text/html; charset=utf-8"%>
Ext.onReady(function() {
			var gridStore = new Ext.data.JsonStore({
				url : 'insureHirer_listPageBy.action',
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
									name : "insureHirer.insureNo",
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
									name : "insureHirer.empCode",
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
									name : "insureHirer.empName",
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
									name : "insureHirer.idcard",
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
									name : "insureHirer.planEffectTmBegin",
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
									name : "insureHirer.planEffectTmEnd",
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
									name : "insureHirer.insureCompany",
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
									name : "insureHirer.effectTmBegin",
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
									name : "insureHirer.effectTmEnd",
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
									hiddenName : "insureHirer.review",
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
									name : "insureHirer.planLoseTmBegin",
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
									name : "insureHirer.planLoseTmEnd",
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
									hiddenName : "insureHirer.status",
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
									name : "insureHirer.loseTmBegin",
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
									name : "insureHirer.loseTmEnd",
									fieldLabel : '失效日期（结束）',
									width : 110
								},{
									xtype : 'hidden',
									name : 'insureHirer.deptId'
								}]
							} ,{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : {
									xtype : "combo",
									hiddenName : "insureHirer.containSubdept",
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
						{header : '状态',width: 60,dataIndex : "status",renderer:statusRender,sortable : false} , 
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
		
			
				// 投保的表单属性
			var editForm_atrr_1 = new Ext.form.TextField({width:150,name:"insureHirer.insureNo",readOnly:true,allowBlank:true,fieldLabel:"投保编号"});

			var editForm_atrr_2 = new Ext.form.TextField({width:150,name:"insureHirer.insureCompany",allowBlank:false,maxLength:100,fieldLabel:"投保单位",labelSeparator:'<font color=red>*</font>'});
			
			var editForm_atrr_4 = new Ext.form.TextField({width:150,name:"insureHirer.empName",allowBlank:false,maxLength:100,fieldLabel:"姓名",labelSeparator:'<font color=red>*</font>'});
			
			var editForm_atrr_5 = new Ext.form.ComboBox({hiddenName:"insureHirer.gender",allowBlank:false,store:new Ext.data.SimpleStore({data: [['男','男'],['女','女']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureHirer.gender",triggerAction:"all",fieldLabel:"性别",editable:false,mode:"local",labelSeparator:'<font color=red>*</font>'});

			var editForm_atrr_6 = new Ext.form.DateField({width:150,name:"insureHirer.borthday",allowBlank:false,format:"Y-m-d",fieldLabel:"出生日期",labelSeparator:'<font color=red>*</font>'});

			var editForm_atrr_7 = new Ext.form.TextField({width:150,name:"insureHirer.idcard",allowBlank:false,minLength:15,maxLength:18,fieldLabel:"身份证号码",validator:function(value){
				 var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
				   if(reg.test(value) === true){  
				      return  true;    
				      }  
				   return "身份证格式错误,长度为15或18，例如432524198811228或432524198811228888或43252419881122888X" ;
			},labelSeparator:'<font color=red>*</font>'});
			
			var editForm_atrr_8 = new Ext.form.DateField({width:150,name:"insureHirer.divisionTm",allowBlank:false,format:"Y-m-d",fieldLabel:"入司时间",labelSeparator:'<font color=red>*</font>'});

			var editForm_atrr_9 = new Ext.form.ComboBox({hiddenName:"insureHirer.persionType",allowBlank:false,store:new Ext.data.SimpleStore({data: [['全日制员工','全日制员工'],['劳务派遣','劳务派遣'],['非全日制','非全日制'],['实习生','实习生'],['勤工助学','勤工助学'],['基地见习生','基地见习生'],['其他','其他']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureHirer.persionType",triggerAction:"all",fieldLabel:"人员类型",editable:false,mode:"local",labelSeparator:'<font color=red>*</font>'});

			var editForm_atrr_10 = new Ext.form.TextField({width:100,name:"insureHirer.deptName",allowBlank:false,fieldLabel:"所属部门",labelSeparator:'<font color=red>*</font>'});

			var editForm_atrr_11 = new Ext.form.TextField({width:150,name:"insureHirer.dutyName",allowBlank:false,fieldLabel:"职位名称",maxLength:100,labelSeparator:'<font color=red>*</font>'});

			var editForm_atrr_12 = new Ext.form.ComboBox({hiddenName:"insureHirer.dutyType",allowBlank:false,store:new Ext.data.SimpleStore({data: [['一线','一线'],['二线','二线'],['三线','三线']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureHirer.dutyType",triggerAction:"all",fieldLabel:"职位属性",editable:false,mode:"local",labelSeparator:'<font color=red>*</font>'});

			var editForm_atrr_13 = new Ext.form.DateField({width:150,name:"insureHirer.planEffectTm",allowBlank:false,format:"Y-m-d",fieldLabel:"拟生效日期",labelSeparator:'<font color=red>*</font>'});

			var editForm_atrr_14 = new Ext.form.DateField({width:150,name:"insureHirer.effectTm",format:"Y-m-d",fieldLabel:"生效日期"});

			var editForm_atrr_15 = new Ext.form.ComboBox({hiddenName:"insureHirer.review",allowBlank:false,store:new Ext.data.SimpleStore({data: [['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureHirer.review",value:"0",triggerAction:"all",fieldLabel:"是否追溯",editable:false,mode:"local",labelSeparator:'<font color=red>*</font>'});

			var editForm_atrr_16 = new Ext.form.TextArea({height:150,width:750,name:"insureHirer.descipt",allowBlank:true,maxLength:1300,fieldLabel:"备注"});
			
			var editForm_atrr_17 = new Ext.form.Hidden({name:"insureHirer.insureId"});
			
			var editForm_atrr_18 = new Ext.form.Hidden({name:"insureHirer.deptId"});
			
			var editForm_atrr_19 = new Ext.form.Hidden({name:"insureHirer.version"});
			
			
			// 有工号修改
			function preUpdateHasEmpCode(){
				editForm_atrr_1.setReadOnly(true); 
				editForm_atrr_2.setReadOnly(false); // 投保单位
				editForm_atrr_3.setReadOnly(true); // 工号
				editForm_atrr_4.setReadOnly(false); // 姓名
				editForm_atrr_5.setReadOnly(true);  // 性别
				editForm_atrr_6.setReadOnly(true);  // 出生日期
				editForm_atrr_7.setReadOnly(false); // 身份证号码
				editForm_atrr_8.setReadOnly(true);	// 入司时间
				editForm_atrr_9.setReadOnly(true);	// 人员类型
				editForm_atrr_10.setReadOnly(true); 	// 所属部门
				editForm_atrr_11.setReadOnly(true);	// 职位名称
				editForm_atrr_12.setReadOnly(false);	// 职位属性
				editForm_atrr_13.setReadOnly(false); // 拟生效日期
				editForm_atrr_14.setReadOnly(true); // 生效日期
				editForm_atrr_15.setReadOnly(false); // 是否追溯
				editForm_atrr_16.setReadOnly(false); // 备注
				
				selectDeptBtn.disable() ;
				saveBtn1.enable() ;
			}
			// 无工号修改
			function preUpdateNoEmpCode(){
				editForm_atrr_1.setReadOnly(true); 
				editForm_atrr_2.setReadOnly(false); // 投保单位
				editForm_atrr_3.setReadOnly(false); // 工号
				editForm_atrr_4.setReadOnly(false); // 姓名
				editForm_atrr_5.setReadOnly(false);  // 性别
				editForm_atrr_6.setReadOnly(false);  // 出生日期
				editForm_atrr_7.setReadOnly(false); // 身份证号码
				editForm_atrr_8.setReadOnly(false);	// 入司时间
				editForm_atrr_9.setReadOnly(false);	// 人员类型
				editForm_atrr_10.setReadOnly(true); 	// 所属部门
				editForm_atrr_11.setReadOnly(false);	// 职位名称
				editForm_atrr_12.setReadOnly(false);	// 职位属性
				editForm_atrr_13.setReadOnly(false); // 拟生效日期
				editForm_atrr_14.setReadOnly(true); // 生效日期
				editForm_atrr_15.setReadOnly(false); // 是否追溯
				editForm_atrr_16.setReadOnly(false); // 备注
				
				selectDeptBtn.enable() ;
				saveBtn1.enable() ;
			}
			// 新增时只容许部分属性可以修改
			function preAdd(){
				editForm_atrr_1.setReadOnly(true); 
				editForm_atrr_2.setReadOnly(false); // 投保单位
				editForm_atrr_3.setReadOnly(false); // 工号
				editForm_atrr_4.setReadOnly(false); // 姓名
				editForm_atrr_5.setReadOnly(false);  // 性别
				editForm_atrr_6.setReadOnly(false);  // 出生日期
				editForm_atrr_7.setReadOnly(false); // 身份证号码
				editForm_atrr_8.setReadOnly(false);	// 入司时间
				editForm_atrr_9.setReadOnly(false);	// 人员类型
				editForm_atrr_10.setReadOnly(true); 	// 所属部门
				editForm_atrr_11.setReadOnly(false);	// 职位名称
				editForm_atrr_12.setReadOnly(false);	// 职位属性
				editForm_atrr_13.setReadOnly(false); // 拟生效日期
				editForm_atrr_14.setReadOnly(true); // 生效日期
				editForm_atrr_15.setReadOnly(false); // 是否追溯
				editForm_atrr_16.setReadOnly(false); // 备注
				
				selectDeptBtn.enable() ;
				saveBtn1.enable() ;
			}
			
			// 生效变更
			function preUpdateSxbg(){
				editForm_atrr_1.setReadOnly(true); 
				editForm_atrr_2.setReadOnly(true); // 投保单位
				editForm_atrr_3.setReadOnly(true); // 工号
				editForm_atrr_4.setReadOnly(false); // 姓名
				editForm_atrr_5.setReadOnly(true);  // 性别
				editForm_atrr_6.setReadOnly(true);  // 出生日期
				editForm_atrr_7.setReadOnly(false); // 身份证号码
				editForm_atrr_8.setReadOnly(true);	// 入司时间
				editForm_atrr_9.setReadOnly(true);	// 人员类型
				editForm_atrr_10.setReadOnly(true); // 所属部门
				editForm_atrr_11.setReadOnly(true);	// 职位名称
				editForm_atrr_12.setReadOnly(true);	// 职位属性
				editForm_atrr_13.setReadOnly(true); // 拟生效日期
				editForm_atrr_14.setReadOnly(true); // 生效日期
				editForm_atrr_15.setReadOnly(true); // 是否追溯
				editForm_atrr_16.setReadOnly(true); // 备注
				
				selectDeptBtn.disable() ;
				// 保存按钮置灰
				saveBtn1.enable() ;
			}
			
			var selectDeptBtn = new Ext.Button({text:"部门",iconCls:'revert',handler:onSelectDept}) ;
			
			var editForm = new Ext.form.FormPanel({region:"center",height:200,frame:true,fileUpload:false,autoWidth:true,timeout:300
				,items: [
					new Ext.Panel({layout:"column",
						items: [
							new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_1]})
							,new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_2]})
							,new Ext.Panel({width:210,layout:"form",id:'editForm_panel_1',items: []})
							,new Ext.Panel({width:50,x:-20,layout:"form",id:'editForm_panel_2',items: []})
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
							,new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_9]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:210,layout:"form",items: [	editForm_atrr_10]})
							,new Ext.Panel({width:90,layout:"form",items: [	selectDeptBtn]})
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
							new Ext.Panel({width:950,layout:"form",items: [editForm_atrr_16	,editForm_atrr_17,editForm_atrr_18,editForm_atrr_19]})
							
						]
					})
				]

			});
			
			//员工选择后的回调方法
			editForm.constructor.prototype.setData = function(data){
				editForm.getForm().findField("insureHirer.empCode").setValue(data.empCode);
				editForm.getForm().findField("insureHirer.empName").setValue(data.empName);
				editForm.getForm().findField("insureHirer.idcard").setValue(data.nationalIdentifier);
				editForm.getForm().findField("insureHirer.divisionTm").setValue(data.sfDate.split("T")[0]);
				editForm.getForm().findField("insureHirer.persionType").setValue(data.personType);
				editForm.getForm().findField("insureHirer.deptName").setValue(data.deptName);
				editForm.getForm().findField("insureHirer.deptId").setValue(data.deptId);
				editForm.getForm().findField("insureHirer.dutyName").setValue(data.empDutyName);
				editForm.getForm().findField("insureHirer.dutyType").setValue(data.empTypeName);
				
				editForm.getForm().findField("insureHirer.gender").setValue(data.empGender);
				editForm.getForm().findField("insureHirer.borthday").setValue(data.dateOfBirth.split("T")[0]);
				
				
				selectDeptBtn.disable() ;
				
				editForm_atrr_5.setReadOnly(true);  // 性别
				editForm_atrr_6.setReadOnly(true);  // 出生日期
				editForm_atrr_8.setReadOnly(true);	// 入司时间
				//editForm_atrr_9.setReadOnly(true);	// 人员类型
				editForm_atrr_11.setReadOnly(true);	// 职位名称
				editForm_atrr_12.setReadOnly(true);	// 职位属性
			};
			
			var editForm_atrr_3 = new SF.ims.empField({width:100,name:"insureHirer.empCode",fieldLabel:"工号",loadForm : editForm});
			
			var clearBtn = new Ext.Button({text:"清空",iconCls:'close',handler:onClear}) ;
			function onClear(){
				if(operator_type == 'add'){
 				   	editForm.getForm().findField("insureHirer.empCode").setValue('') ;
					editForm.getForm().findField("insureHirer.empName").setValue('');
					editForm.getForm().findField("insureHirer.idcard").setValue('');
					editForm.getForm().findField("insureHirer.divisionTm").setValue('');
					editForm.getForm().findField("insureHirer.persionType").setValue('');
					editForm.getForm().findField("insureHirer.deptName").setValue('');
					editForm.getForm().findField("insureHirer.dutyName").setValue('');
					editForm.getForm().findField("insureHirer.dutyType").setValue('');
					
					editForm.getForm().findField("insureHirer.gender").setValue('');
					editForm.getForm().findField("insureHirer.borthday").setValue('');
					
					editForm.getForm().findField("insureHirer.deptId").setValue('');
					
					selectDeptBtn.enable() ;
					
					editForm_atrr_5.setReadOnly(false);  // 性别
					editForm_atrr_6.setReadOnly(false);  // 出生日期
					editForm_atrr_8.setReadOnly(false);	// 入司时间
					editForm_atrr_9.setReadOnly(false);	// 人员类型
					editForm_atrr_11.setReadOnly(false);	// 职位名称
					editForm_atrr_12.setReadOnly(false);	// 职位属性
				   	}
			}
			
			Ext.getCmp('editForm_panel_1').items.add(editForm_atrr_3);
			Ext.getCmp('editForm_panel_2').items.add(clearBtn);

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
				          			editForm.getForm().findField("insureHirer.deptName").setValue(selectDeptNode.text);
				          			editForm.getForm().findField("insureHirer.deptId").setValue(selectDeptNode.id);
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
			
			// 投保form
			var editWin = new Ext.Window({height:400,constrainHeader:true,width:950,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
				,tbar: [
				        saveBtn1
				]
				,items: [
				         editForm
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
							        <app:isPermission code="/insure/insureHirerSearch.action">
							        ,new Ext.Button({text:"查询",iconCls:'search',handler:onSearch })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureHirerDetail.action">
							        ,new Ext.Button({text:"查看",iconCls:'detail',handler:onDetail }) 
							        </app:isPermission>
							        <app:isPermission code="/insure/insureHirerAdd.action">
							        ,new Ext.Button({text:"投保",iconCls:'add',handler:onAdd })
							        </app:isPermission>
							          <app:isPermission code="/insure/insureHirerImportAdd.action">
							        ,new Ext.Button({text:"导入投保",iconCls:'add',handler:onImportAdd })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureHirerEdit.action">
							        ,new Ext.Button({text:"修改",iconCls:'edit',handler:onUpdate })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureHirerDel.action">
							        ,new Ext.Button({text:"删除",iconCls:'delete',handler:onDelete })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureHirerEffect.action">
							        ,new Ext.Button({text:"生效",iconCls:'apply',handler:onSx })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureHirerImportEffect.action">
							        ,new Ext.Button({text:"导入生效",iconCls:'apply',handler:onDrsx })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureHirerEffectUpdate.action">
							        ,new Ext.Button({text:"生效变更",iconCls:'edit',handler:onUpdateSx })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureHirerReduce.action">
							        ,new Ext.Button({text:"减保",iconCls:'delete',handler:onJb })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureHirerExpire.action">
							        ,new Ext.Button({text:"失效",iconCls:'close',handler:onJbsx })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureHirerImportExpire.action">
							        ,new Ext.Button({text:"导入失效",iconCls:'close',handler:onDrjbsx })
							        </app:isPermission>
							        <app:isPermission code="/insure/insureHirerExport.action">
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


			/** *******************生效*********************** */
			// 生效
			var sx_form_attr_1 = new Ext.form.DateField({width:150,name:"insureHirer.effectTm",allowBlank:false,format:"Y-m-d",fieldLabel:"生效日期<font color=red>*</font>"});
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
						var effect_Tm = sx_form.getForm().findField('insureHirer.effectTm').getRawValue();
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
						
						var effectTm = sx_form.getForm().findField('insureHirer.effectTm').getValue();
						Ext.Ajax.request({params: {'ids': ids,'insureHirer.effectTm':effectTm},
							url: "insureHirer_effect.action",
							success: function(response) {
								var resp = Ext.util.JSON.decode(response.responseText);
								if (resp.success) {
									Ext.MessageBox.alert('提示','操作成功');
									sx_win.hide();
									gridPanel.getStore().reload();
								} else {
									Ext.MessageBox.alert('操作失败', resp.msg);
									gridPanel.getStore().reload();
									sx_win.hide() ;
								}
							},
							failure:ajaxRequestFailure
						});
					}
				}
			
			/** *******************减保*********************** */
			// 减保form属性
			var jb_form_attr_1 = new Ext.form.DateField({width:150,name:"insureHirer.planLoseTm",allowBlank:false,format:"Y-m-d",fieldLabel:"拟失效日期<font color=red>*</font>"});
			
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
						var planLoseTm = jb_form.getForm().findField('insureHirer.planLoseTm').getRawValue();
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
						
						Ext.Ajax.request({params: {'ids': ids,'insureHirer.planLoseTm':planLoseTm},
							url: "insureHirer_reduceInsure.action",
							success: function(response) {
								var resp = Ext.util.JSON.decode(response.responseText);
								if (resp.success) {
									Ext.MessageBox.alert('提示','操作成功');
									jb_win.hide();
									gridPanel.getStore().reload();
								} else {
									Ext.MessageBox.alert('操作失败', resp.msg);
									jb_win.hide() ;
									gridPanel.getStore().reload();
								}
							},
							failure:ajaxRequestFailure
						});
					}
				}
			/** *******************失效*********************** */
			// 失效form属性
			var jbsx_form_attr_1 = new Ext.form.DateField({width:150,name:"insureHirer.loseTm",allowBlank:false,format:"Y-m-d",fieldLabel:"失效日期<font color=red>*</font>"});

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
						var loseTm = jbsx_form.getForm().findField('insureHirer.loseTm').getRawValue();
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
						
						
						Ext.Ajax.request({params: {'ids': ids,'insureHirer.loseTm':loseTm},
							url: "insureHirer_unEffect.action",
							success: function(response) {
								var resp = Ext.util.JSON.decode(response.responseText);
								if (resp.success) {
									Ext.MessageBox.alert('提示','操作成功');
									jbsx_win.hide();
									gridPanel.getStore().reload();
								} else {
									Ext.MessageBox.alert('操作失败', resp.msg);
									jbsx_win.hide() ;
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
					editWin.setTitle("生效变更");
					editWin.show();
					
					var obj = {};
					for (p in record.data) {
						if (p == 'divisionTm' || p == 'planEffectTm' || p == 'effectTm' || p == 'borthday' ) {
							if(record.data[p] != null){
								editForm.getForm().findField('insureHirer.' + p).setValue(record.data[p].split('T')[0]);
							}else{
								editForm.getForm().findField('insureHirer.' + p).setValue('');
							}
							
							continue;
						}
						obj['insureHirer.' + p] = record.data[p]; 
					}
					editForm.getForm().setValues(obj);
					
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
							html : "&nbsp;&nbsp;<a style=\"text-decoration:none;color:#11439C;line-height:23px;font-size:12;padding-top:2px;height:25px;text-overflow:ellipsis;white-space:nowrap;overflow:hidden;\"  onmouseover=\"this.style.color=\'red\';this.style.cursor=\'hand\'\" onmouseout=\"this.style.color=\'#11439C\'\" href=\'#\' onClick=\"window.location ='insureHirer_downloadTpl.action';\"><img src=\"../images/insure/download.gif\" /><span>导入模板.xls</span></a>"
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
				importWin.url = 'insureHirer_importEffect.action';
				importWin.show() ;
			}
		
			// 导入失效
			function onDrjbsx(){
				importWin.setTitle("导入失效");
				importWin.url = 'insureHirer_importUneffect.action';
				importWin.show() ;
			}
			
			
			// 导入投保
			function onImportAdd(){
				importWin.setTitle("导入投保");
				importWin.url = 'insureHirer_importAdd.action';
				importWin.show() ;
			}
			
			// 查询
			function onSearch() {
				queryForm.getForm().findField('insureHirer.deptId').setValue(
						his_node.id);

				if (queryForm.getForm().isValid() == false) {
					Ext.MessageBox.alert("提示","数据校验错误！");
				} else {
					// 判断拟生效日期
					var planEffectTmBegin = queryForm.getForm().findField('insureHirer.planEffectTmBegin').getValue();
					var planEffectTmEnd = queryForm.getForm().findField('insureHirer.planEffectTmEnd').getValue();
					if (planEffectTmBegin != '' && planEffectTmEnd != '') {
						if (planEffectTmBegin > planEffectTmEnd) {
							Ext.Msg.alert("提示", "拟生效开始日期不能大于结束日期");
							return ;
						}
					}
					// 判断生效日期
					var effectTmBegin = queryForm.getForm().findField('insureHirer.effectTmBegin').getValue();
					var effectTmEnd = queryForm.getForm().findField('insureHirer.effectTmEnd').getValue();
					if (effectTmBegin != '' && effectTmEnd != '') {
						if (effectTmBegin > effectTmEnd) {
							Ext.Msg.alert("提示", "生效开始日期不能大于结束日期");
							return ;
						}
					}
					// 判断拟失效日期
					var planLoseTmBegin = queryForm.getForm().findField('insureHirer.planLoseTmBegin').getValue();
					var planLoseTmEnd = queryForm.getForm().findField('insureHirer.planLoseTmEnd').getValue();
					if (planLoseTmBegin != '' && planLoseTmEnd != '') {
						if (planLoseTmBegin > planLoseTmEnd) {
							Ext.Msg.alert("提示", "拟失效开始日期不能大于结束日期");
							return ;
						}
					}
					// 判断失效日期
					var loseTmBegin = queryForm.getForm().findField('insureHirer.loseTmBegin').getValue();
					var loseTmEnd = queryForm.getForm().findField('insureHirer.loseTmEnd').getValue();
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
					editForm.getForm().findField("insureHirer.deptName").setValue(his_node.text);
					editForm.getForm().findField("insureHirer.deptId").setValue(his_node.id);
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
					
					editWin.setTitle("修改");
					editWin.show();
					var obj = {};
					for (p in record.data) {
						if (p == 'divisionTm' || p == 'planEffectTm' || p == 'effectTm' || p == 'borthday' ) {
							if(record.data[p] != null){
								editForm.getForm().findField('insureHirer.' + p).setValue(record.data[p].split('T')[0]);
							}else{
								editForm.getForm().findField('insureHirer.' + p).setValue('');
							}
							
							continue;
						}
						obj['insureHirer.' + p] = record.data[p]; 
					}
					editForm.getForm().setValues(obj);
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
				if (editForm.getForm().isValid() == false) {
					Ext.MessageBox.alert("提示","数据校验错误！");
					return;
				}
				// 检验身份证号码格式
				/*var idcard = editForm.getForm().findField("insureHirer.idcard").getValue() ;
				if(!checkIdcardFormate(idcard)){
					Ext.MessageBox.alert("提示","身份证格式错误");
					return  ;
				}
				*/
				var waitMsg;
				if (operator_type == 'add') {
					waitMsg = "正在执行保存操作...";
					editForm.getForm().url = "insureHirer_save.action";
					//保存时校验是否有部门权限
					//权限验证
					var deptid = editForm.getForm().findField("insureHirer.deptId").getValue();
	          		authorizedDepartment(deptid,function(){
	          			editForm.getForm().submit(
								{
									success : showSaveOrUpdateSuccessInfo,
									failure : showSaveOrUpdateFailureInfo,
									waitMsg : waitMsg,
									waitTitle : "请稍后..."
								});
					
				 }.createDelegate(this));
				} else if (operator_type == 'update') {
					waitMsg = "正在执行修改操作...";
					editForm.getForm().url = "insureHirer_update.action";
					editForm.getForm().submit(
							{
								success : showSaveOrUpdateSuccessInfo,
								failure : showSaveOrUpdateFailureInfo,
								waitMsg : waitMsg,
								waitTitle : "请稍后..."
							});
				}else if (operator_type == 'updateEffect') {
					waitMsg = "正在执行修改操作...";
					editForm.getForm().url = "insureHirer_updateEffect.action";
					editForm.getForm().submit(
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
					} else if (operator_type == 'update') {
						Ext.Msg.alert("提示","更新成功");
					}else if (operator_type == 'updateEffect') {
						Ext.Msg.alert("提示","更新成功");
					}
					editWin.hide();
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
					url: "insureHirer_exportValidate.action",
					success: function(response) {
						var resp = Ext.util.JSON.decode(response.responseText);
						if (resp.success) {
							queryForm.getForm().doAction('submit', {
								url : "exportInsureHirers.action",
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
							url: "insureHirer_delete.action",
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
			
			
			// 详情的表单属性
			var detailForm_atrr_1 = new Ext.form.TextField({width:150,name:"insureHirer.insureNo",readOnly:true,fieldLabel:"投保编号"});
			
			var detailForm_atrr_2 = new Ext.form.ComboBox({hiddenName:"insureHirer.status",readOnly:true,store:new Ext.data.SimpleStore({data: [['1','拟生效'],['2','生效'],['3','拟失效'],['4','失效']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureHirer.review",triggerAction:"all",fieldLabel:"状态",editable:false,mode:"local"});

			var detailForm_atrr_3 = new Ext.form.TextField({width:150,name:"insureHirer.areaName",readOnly:true,fieldLabel:"所属地区"});

			var detailForm_atrr_4 = new Ext.form.TextField({width:150,name:"insureHirer.insureCompany",readOnly:true,fieldLabel:"投保单位"});
			
			var detailForm_atrr_5 = new Ext.form.TextField({width:150,name:"insureHirer.deptName",readOnly:true,fieldLabel:"所属部门"});
			
			var detailForm_atrr_6 = new Ext.form.TextField({width:150,name:"insureHirer.empCode",readOnly:true,fieldLabel:"工号"});
			
			var detailForm_atrr_7 = new Ext.form.TextField({width:150,name:"insureHirer.empName",readOnly:true,fieldLabel:"姓名"});

			var detailForm_atrr_8 = new Ext.form.TextField({width:150,name:"insureHirer.idcard",readOnly:true,fieldLabel:"身份证号码"});
			
			var detailForm_atrr_9 = new Ext.form.ComboBox({hiddenName:"insureHirer.gender",readOnly:true,store:new Ext.data.SimpleStore({data: [['男','男'],['女','女']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureHirer.gender",value:"男",triggerAction:"all",fieldLabel:"性别",editable:false,mode:"local"});

			var detailForm_atrr_10 = new Ext.form.DateField({width:150,name:"insureHirer.borthday",readOnly:true,format:"Y-m-d",fieldLabel:"出生日期"});

			var detailForm_atrr_11 = new Ext.form.DateField({width:150,name:"insureHirer.divisionTm",readOnly:true,format:"Y-m-d",fieldLabel:"入司时间"});

			var detailForm_atrr_12 = new Ext.form.TextField({width:150,name:"insureHirer.persionType",readOnly:true,fieldLabel:"人员类型"});

			var detailForm_atrr_13 = new Ext.form.TextField({width:150,name:"insureHirer.dutyName",readOnly:true,fieldLabel:"职位名称"});

			var detailForm_atrr_14 = new Ext.form.TextField({width:150,name:"insureHirer.dutyType",readOnly:true,fieldLabel:"职位属性"});

			var detailForm_atrr_15 = new Ext.form.ComboBox({hiddenName:"insureHirer.review",readOnly:true,store:new Ext.data.SimpleStore({data: [['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureHirer.review",triggerAction:"all",fieldLabel:"是否追溯",editable:false,mode:"local"});

			var detailForm_atrr_16 = new Ext.form.DateField({width:150,name:"insureHirer.planEffectTm",readOnly:true,format:"Y-m-d",fieldLabel:"拟生效日期"});

			var detailForm_atrr_17 = new Ext.form.DateField({width:150,name:"insureHirer.effectTm",readOnly:true,format:"Y-m-d",fieldLabel:"生效日期"});
			
			var detailForm_atrr_18 = new Ext.form.DateField({width:150,name:"insureHirer.planLoseTm",readOnly:true,format:"Y-m-d",fieldLabel:"拟失效日期"});

			var detailForm_atrr_19 = new Ext.form.DateField({width:150,name:"insureHirer.loseTm",readOnly:true,format:"Y-m-d",fieldLabel:"失效日期"});
			
			var detailForm_atrr_20 = new Ext.form.TextField({width:150,name:"insureHirer.days",readOnly:true,fieldLabel:"在保天数"});

			var detailForm_atrr_21 = new Ext.form.TextField({width:150,name:"insureHirer.totalInsureFee",readOnly:true,fieldLabel:"保费(元)"});
			
			var detailForm_atrr_22 = new Ext.form.TextField({width:150,name:"insureHirer.createEmpName",readOnly:true,fieldLabel:"创建人"});
			
			var detailForm_atrr_23 = new Ext.form.TextField({width:150,name:"insureHirer.createTm",readOnly:true,fieldLabel:"创建时间"});
			
			var detailForm_atrr_24 = new Ext.form.TextField({width:150,name:"insureHirer.updateEmpName",readOnly:true,fieldLabel:"修改人"});
			
			var detailForm_atrr_25 = new Ext.form.TextField({width:150,name:"insureHirer.updateTm",readOnly:true,fieldLabel:"修改时间"});

			var detailForm_atrr_26 = new Ext.form.TextArea({height:150,width:750,name:"insureHirer.descipt",readOnly:true,fieldLabel:"备注"});
			

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
							new Ext.Panel({width:300,layout:"form",items: [	detailForm_atrr_22]})
							,new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_23]})
							,new Ext.Panel({width:300,layout:"form",items: [detailForm_atrr_24]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [	detailForm_atrr_25]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:950,layout:"form",items: [detailForm_atrr_26]})
							
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
								detailForm.getForm().findField('insureHirer.' + p).setValue(record.data[p].split('T')[0]);
							}else{
								detailForm.getForm().findField('insureHirer.' + p).setValue('');
							}
							continue;
						}
						if (p == 'createTm' || p == 'updateTm') {
							if(record.data[p] != null){
								detailForm.getForm().findField('insureHirer.' + p).setValue(record.data[p].replace('T',' '));
							}else{
								detailForm.getForm().findField('insureHirer.' + p).setValue('');
							}
							continue;
						}
						obj['insureHirer.' + p] = record.data[p]; 
					}
					detailForm.getForm().setValues(obj);
					
				} else if (records.length < 1) {
					Ext.Msg.alert("提示","请选择一条记录");
				} else if (records.length > 1) {
					Ext.Msg.alert("提示","不能同时操作多条记录");
				}
			}
			
		});