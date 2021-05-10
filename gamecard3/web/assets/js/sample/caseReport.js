<%@ page language="java" contentType="text/html; charset=utf-8"%>

Ext.onReady(function() {
			var gridStore = new Ext.data.JsonStore({
				url : 'case_listPageBy.action?caseObject.queryType=report',
				fields : [ {name : 'caseId'}, 
				           {name : 'eventId'}, 
				           {name : 'insuretypeId'}, 
				           {name : 'insuretypeName'}, 
				           {name : 'caseLevel'}, 
				           {name : 'caseOccourTime'},
				           {name : 'caseFindTime'},
				           {name : 'casePlace'}, 
				           {name : 'caseDesc'},
				           {name : 'reportUser'	},
				           {name : 'reportUserName'	},
				           {name : 'reportDept'}, 
				           {name : 'reportDeptName'}, 
				           {name : 'reportContact'}, 
				           {name : 'reportTm'},
				           {name : 'reportCompanyTm'},
				           {name : 'modifyUser'}, 
				           {name : 'modifyTm'},
				           {name : 'status'} 
				           
				],
				totalProperty : 'totalSize',
				id:'caseId',
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
			
			
			var queryForm_atrr_1 = new Ext.form.DateField({width:125,name:"caseObject.reportTmBegin",format:"Y-m-d",fieldLabel:"上报时间（开始）"});

			var queryForm_atrr_2 = new Ext.form.DateField({width:125,name:"caseObject.reportTmEnd",format:"Y-m-d",fieldLabel:"上报时间（结束）"});

			var queryForm_atrr_3 = new Ext.form.ComboBox({hiddenName:"caseObject.status",store:new Ext.data.SimpleStore({data: [['','全部'],['1','新增'],['2','上报']], fields: ['key','value']}),valueField:"key",displayField:"value",width:125,triggerAction:"all",emptyText : '全部',fieldLabel:"状态",editable:false,mode:"local"});

			var queryForm_atrr_4 = new Ext.form.TextField({width:125,name:"caseObject.caseId",fieldLabel:"案件工单号"});
			
			var queryForm_atrr_5 = new Ext.form.TextField({width:125,name:"caseObject.eventId",fieldLabel:"事件工单号"});

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
				          			queryForm.getForm().findField("caseObject.reportDeptName").setValue(selectDeptNode.text);
				          			queryForm.getForm().findField("caseObject.reportDept").setValue(selectDeptNode.id);
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
							selectDeptWin.setTitle("选择上报地区/部门"+"<font color='red'> ->"+node.text+"</font>");
						}
					}		
				}
				]
			});
			var queryForm_atrr_6 = new Ext.form.TriggerField({
									width:125,
									name:"caseObject.reportDeptName",
									fieldLabel:"上报地区/部门",
									triggerClass : 'x-form-search-trigger',
									editable:false,
									enableKeyEvents :true,
									listerners:{
									},
									onTriggerClick:function(){
										selectDeptWin.setTitle("选择上报地区/部门");
										selectDeptWin.show() ;
									}
				
			});
			var queryForm_atrr_7 = new Ext.form.ComboBox({hiddenName:"caseObject.isContainSubDept",store:new Ext.data.SimpleStore({data: [['1','包含'],['2','不包含']], fields: ['key','value']}),valueField:"key",displayField:"value",value:"1",width:125,triggerAction:"all",fieldLabel:"所辖网点",editable:false,mode:"local"});

			var queryForm_atrr_8 = new Ext.form.Hidden({name:"caseObject.reportDept"});
			
			var queryForm_atrr_9 = new Ext.form.Hidden({name:"caseObject.insuretypeId"});

			
			var queryForm = new Ext.form.FormPanel({
				frame : true,
				region : "north",
				autoHeight : true,
				fileUpload:true,
				items : [

				new Ext.form.FieldSet({
					labelWidth : 100,
					autoHeight:true,
					title : "查询条件",
					layout : 'column',
					items : [
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_1]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_2]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_3]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_4]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_5]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_6]
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 120,
								labelAlign : "left",
								layout : "form",
								items : [queryForm_atrr_7,queryForm_atrr_8,queryForm_atrr_9]
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
			// 日期显示格式
			var dispalyDateFormat2 = new Ext.form.DateField({
			maxLength:20,
			width:120,
			disabled:false,
			format:"Y-m-d H:i"});
			
			
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
				cm : new Ext.grid.ColumnModel([ 
				        {renderer:showMenu,dataIndex:"menu",width:30,id:"grid_menu"},
						{header : '案件工单号',width: 100,dataIndex : "caseId",	sortable : false},
						{header : '事件工单号',width: 100,dataIndex : "eventId",sortable : false} , 
						{header : '案件类型',width: 100,dataIndex : "insuretypeName",sortable : false},
						{header : '状态',width: 100,dataIndex : "status",renderer:statusRenderer,sortable : false}, 
						{header : '上报地区/部门',width: 150,dataIndex : "reportDeptName",sortable : false},
						{header : '上报时间',width: 80,dataIndex : "reportTm",renderer:datetimeRenderer,sortable : false,id:"dispalyDateFormat"} ,
						{header : '上报人',width: 80,dataIndex : "reportUserName",sortable : false} ,
						{header : '上报人联系方式',width: 120,dataIndex : "reportContact",sortable : false} ,
						{header : '案件发现时间',width: 110,dataIndex : "caseFindTime",renderer:datetimeRenderer,sortable : false,id:"dispalyDateFormat2"} ,
						{header : '案件发生时间',width: 110,dataIndex : "caseOccourTime",renderer:datetimeRenderer,sortable : false,id:"dispalyDateFormat2"} ,
						{header : '发生地点',width: 150,dataIndex : "casePlace",sortable : false} ,
						{header : '案件描述',width: 150,dataIndex : "caseDesc",sortable : false} 
						]),
				listeners:{cellclick:showOperation,rowcontextmenu:rightMenu}
			});

			//右键菜单
			function rightMenu(grid,rowIndex,e)
			{
			   showOperation(grid,rowIndex,0,e);
			}   
			function showOperation(grid,rowIndex,columnIndex,e){
				if(columnIndex == 0){
					var  recode = gridStore.getAt(rowIndex);
					 var rightClick = new Ext.menu.Menu( {                
			                width:100
			            });
				    var detailMenu = {
		                    iconCls:'detail',
		                    text:'查看',
		                    handler:function()
		                    {
		                       rightClick.hide(true);
		                    }
		                };
		         	rightClick.add(detailMenu);
		         	
		         	 var editMenu = {
			                    iconCls:'edit',
			                    text:'修改',
			                    handler:function()
			                    {
			                       rightClick.hide(true);
			                       onEdit(rowIndex) ;
			                    }
			                };
		         	 rightClick.add(editMenu);
		         	 
		         	  var commitMenu = {
			                    iconCls:'apply',
			                    text:'提交',
			                    handler:function()
			                    {
			                       rightClick.hide(true);
			                       onCommit(rowIndex) ;
			                    }
			                };
		         	 rightClick.add(commitMenu);
			        
		         	e.preventDefault();
		            rightClick.showAt(e.getXY());
				}
				
				
			}
			//显示列表菜单
			function showMenu(value, cellmeta, record, rowIndex, columnIndex, store){
				        return "<image title='' style='cursor:hand;' src='../images/claim/select.gif' style='width:22px;height:20px;'></image>";
				    }
			function statusRenderer(v){
				var value="" ;
				if(v == '1'){
					value="新增" ;
				}else if(v == '2'){
					value="上报" ;
				}else if(v == '3'){
					value="处理" ;
				}else if(v == '4'){
					value="完结" ;
				}else if(v == '5'){
					value="评估" ;
				}
				return value ;
			}
			
			var treeLoader = new Ext.tree.TreeLoader({dataUrl:"processDeal_searchTreeByParentId.action?textField=name&idField=insureTypeId&leafField=&clsField=&childrenField=",url:"processDeal_searchTreeByParentId.action?textField=name&idField=insureTypeId&leafField=&clsField=&childrenField="});

			var rootNode = new Ext.tree.AsyncTreeNode({id:"1",text:"保险类型",expanded:true});
			var his_node ;
			var treePanel = new Ext.tree.TreePanel({region:"west",title:" ",checkModel:"multiple",autoScroll:true,collapsible:true,width:200,onlyLeafCheckable:false
							,loader: treeLoader
							,root: rootNode
							,listeners : {
								click : function(node) {
									his_node = node ;
									onSearch();
								}
							}	
						});
			
			new Ext.Viewport({
				layout : 'border',
				items : [
				         treePanel,
						{
							region : 'center',
							tbar : [ '-'
							        <app:isPermission code="/insure/insureHirerSearch.action">
							        ,new Ext.Button({text:"查询",iconCls:'search',handler:onSearch })
							        </app:isPermission>
							         ],
							layout : 'border',
							items : [queryForm,gridPanel]
						} ],
						listeners : {
							afterrender : function(view){
								his_node = view.items.get(0).getRootNode() ;
								onSearch() ;
							}
						}
			});

			// 查询
			function onSearch() {
				queryForm.getForm().findField('caseObject.insuretypeId').setValue(
						his_node.id);
				if (queryForm.getForm().isValid() == false) {
					Ext.MessageBox.alert("提示","数据校验错误！");
					return ;
				} 
					
				gridPanel.getStore().baseParams = queryForm.getForm().getValues();
				gridPanel.getStore().baseParams["limit"] = pagingBar.pageSize;
				gridPanel.getStore().load();
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
			
			
			/****************base form*******************/
			var baseFieldSet = new Ext.form.FieldSet({
				labelWidth : 100,
				autoHeight:true,
				title:"基础信息",
				layout : 'column',
				items : [
						{
							xtype : "panel",
							columnWidth : .33,
							labelAlign : "left",
							layout : "form",
							items : [{
										xtype:"textfield",
										name:"caseObject.caseId",
										readOnly:true,
										fieldLabel:"案件工单号"
										}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									name:"caseObject.reportDeptName",
									readOnly:true,
									fieldLabel:"上报地区/部门"
									}]

						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									name:"caseObject.reportUserName",
									readOnly:true,
									fieldLabel:"上报人"
									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									name:"caseObject.reportContact",
									readOnly:true,
									fieldLabel:"上报人联系方式"
									}]
						},
						
						{
							xtype : "panel",
							columnWidth : .33,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									name:"caseObject.caseOccourTime",
									readOnly:true,
									fieldLabel:"事件发生时间"
									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									name:"caseObject.caseFindTime",
									readOnly:true,
									fieldLabel:"事件发现时间"
									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									name:"caseObject.casePlace",
									readOnly:true,
									fieldLabel:"发生地点"
									}]
						},
							{
							xtype : "panel",
							columnWidth : .33,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									name:"caseObject.insuretypeName",
									readOnly:true,
									fieldLabel:"案件类型"
									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"datefield",
									name:"caseObject.reportCompanyTm",
									fieldLabel:"上报保险公司时间",
									format:'Y-m-d',
									allowBlank:false,
									anchor:'95%'
									}]
						},
					
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:'textarea',
									height:80,
									width:600,
									name:"caseObject.caseDesc",
									readOnly:true,
									fieldLabel:"案件描述"
									}]
						}
		         ]
				});
			
			var checkboxSelectionMode2 = new Ext.grid.CheckboxSelectionModel({});
			
			
			function causeEventRenderer(v){
				var value="";
				if(v == '1'){
					value="未引发其它事件" ;
				}else if(v == '2'){
					value="劳动争议" ;
				}else if(v =='3'){
					value="法律诉讼" ;
				}else if(v == '4'){
					value ="外部侵害" ;
				}else if(v == '5'){
					value="媒体事件" ;
				}else if(v=='6'){
					value="其它";
				}
				return value ;
			}
			
			function status2Renderer(v){
				var value = "" ;
				if(v == '1'){
					value = "<font color='red'>新增</font>-->上报-->处理-->完结-->评估" ;
				}else if(v == '2'){
					value = "新增--><font color='red'>上报</font>-->处理-->完结-->评估" ;
				}else if(v == '3'){
					value = "新增-->上报--><font color='red'>处理</font>-->完结-->评估" ;
				}else if(v == '4'){
					value = "新增-->上报-->处理--><font color='red'>完结</font>-->评估" ;
				}else if(v == '5'){
					value = "新增-->上报-->处理-->完结--><font color='red'>评估</font>" ;
				}
				return value ;
			}
			
			
			
			var baseForm = new Ext.form.FormPanel({
				frame:true,
				autoHeight : true,
				width:764,
				collapsible:true,
				title:"基础数据",
				items : [
				    baseFieldSet
				]
			});
			
			
			/**************************************/
			var btnSave = new Ext.Button({text:"保存",iconCls:'save',handler:onSave });
			//案件处理窗口
			var configWindow = new Ext.Window({
				modal:true,plain:true,
				resizable:false,closeAction:"hide",
				layout:"fit",
				width:800,closable:true,height:600,title:"案件处理",
				tbar: [
						btnSave
				],
				items: [{
					autoScroll:true,
					frame:true,
					items:[
						       {
								//基础数据
								autoHeight:true,
								items:[baseForm]
								},
								{
								autoHeight:true,
								id:"personalTplPanel"
								}
							]
				}]
			});
		
			//保存处理信息
			function onSave(){
				var personalForm = Ext.getCmp("personalTplPanel").items.get(0).getForm();
				if (!baseForm.getForm().isValid()||!personalForm.isValid()) {
					Ext.MessageBox.alert("提示","数据校验错误！");
					return ;
				} 
				
				personalForm.submit(
						{
							params:baseForm.getForm().getValues(),
							success : showSaveOrUpdateSuccessInfo,
							failure : showSaveOrUpdateFailureInfo,
							waitMsg : "正在执行处理操作...",
							waitTitle : "请稍后..."
						});
				
			}
			function showSaveOrUpdateSuccessInfo(form, action) {
				if (action.result.msg) {
					Ext.Msg.alert("提示",
							action.result.msg);
				} else {
					Ext.MessageBox.alert("提示","操作成功");
					//TODO
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
			
			//案件上报-修改
			function onEdit(rowIndex){
				//TODO 修改
				var  record = gridStore.getAt(rowIndex);
				//根据案件类型更换个性化模板
				var insureTypeId = record.data.insuretypeId ;
				var personalForm ;
				if(insureTypeId == '5'){//雇主责任险模板
					personalForm = new SF.claim.hirer.PersonalHirerForm();
				}else if(insureTypeId == '6'){ //员工意外险模板
					personalForm = new SF.claim.emp.PersonalEmpForm();
				}
				var personalTplPanel = Ext.getCmp("personalTplPanel");
				personalTplPanel.removeAll();
				personalTplPanel.insert(0,personalForm);
				
				configWindow.setTitle("案件上报");
				configWindow.show();
				baseForm.getForm().reset() ;
				/**************************/
				//填充基础信息数据
				var baseObj = {};
				for (var p in record.data) {
					if(p == 'caseOccourTime' || p == 'caseFindTime'){
						var time = record.data[p].split("T")[1] ;
						baseObj['caseObject.' + p] = record.data[p].split("T")[0]+" "+time.split(":")[0]+":"+time.split(":")[0] ;
						continue ;
					}
					if(p == 'reportCompanyTm' && record.data[p] != null){
						baseObj['caseObject.' + p] = record.data[p].split("T")[0] ;
						continue ;
					}
					baseObj['caseObject.' + p] = record.data[p]; 
				}
				baseForm.getForm().setValues(baseObj);
				//个性化数据
				var insureTypeurl ;
				
				if(insureTypeId == '5'){//雇主责任险模板
					setPersonalHirerData(personalForm,record.data.caseId) ;
				}else if(insureTypeId == '6'){ //员工意外险模板
					setPersonalEmpData(personalForm,record.data.caseId) ;
				}
			}
			
			function onCommit(rowIndex){
				var record = gridStore.getAt(rowIndex);
				Ext.Ajax.request({
								params: {caseId: record.id},
								url: "case_report.action",
								success:function(response,opts){
									var msg = Ext.decode(response.responseText).msg;
									var error = Ext.decode(response.responseText).error;
									if(msg||error){
										Ext.Msg.alert('提示',msg||error);
									}else{
										Ext.Msg.alert('提示','提交成功!');
										gridStore.reload();
									}
								}.createDelegate(this),
								failure:function(response,opts){
									var msg = Ext.decode(response.responseText).msg;
									Ext.Msg.alert("提示",msg);
								}
							});			
	
			}
			//设置雇主责任险的个性化数据
			function setPersonalHirerData (personalForm,caseId){
				Ext.Ajax.request({params: {"caseId": caseId},
					url: "caseHirer_find.action",
					success: function(response) {
						var resp = Ext.util.JSON.decode(response.responseText);
						if (resp.success) {
							var Object = resp.returnObject ;
							var obj = {};
							for (var p in Object) {
								if(p == 'divitionTm' && Object[p] != null){
									obj['caseHirer.'+p] = Object[p].split("T")[0] ;
									continue ;
								}
								obj['caseHirer.'+p] = Object[p]; 
							}
							personalForm.getForm().setValues(obj);
						} else {
						}
					},
					failure:ajaxRequestFailure
				});
			}
			//设置员工意外险的个性化数据
			function setPersonalEmpData (personalForm,caseId){
				Ext.Ajax.request({params: {"caseId": caseId},
					url: "caseEmp_find.action",
					success: function(response) {
						var resp = Ext.util.JSON.decode(response.responseText);
						if (resp.success) {
							var Object = resp.returnObject ;
							var obj = {};
							for (var p in Object) {
								if(p == 'divitionTm' && Object[p] != null){
									obj['caseEmp.'+p] = Object[p].split("T")[0] ;
									continue ;
								}
								obj['caseEmp.'+p] = Object[p]; 
							}
							personalForm.getForm().setValues(obj);
						} else {
						}
					},
					failure:ajaxRequestFailure
				});
			}
			
		});