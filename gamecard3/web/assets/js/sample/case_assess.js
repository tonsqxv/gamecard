<%@ page language="java" contentType="text/html; charset=utf-8"%>

Ext.onReady(function() {
			var gridStore = new Ext.data.JsonStore({
				url : 'case_listPageBy.action?caseObject.queryType=assess',
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
				           {name : 'status'} ,
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
			
			
			var queryForm_atrr_1 = new Ext.form.DateField({width:125,name:"caseObject.reportTmBegin",format:"Y-m-d",fieldLabel:"上报时间（开始）"});

			var queryForm_atrr_2 = new Ext.form.DateField({width:125,name:"caseObject.reportTmEnd",format:"Y-m-d",fieldLabel:"上报时间（结束）"});

			var queryForm_atrr_3 = new Ext.form.ComboBox({hiddenName:"caseObject.status",store:new Ext.data.SimpleStore({data: [['','全部'],['4','完结'],['5','评估']], fields: ['key','value']}),valueField:"key",displayField:"value",width:125,triggerAction:"all",emptyText : '全部',fieldLabel:"状态",editable:false,mode:"local"});

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
					var  recorde = gridStore.getAt(rowIndex);
					 var rightClick = new Ext.menu.Menu( {                
			                width:100
			            });
				    var detailMenu = {
		                    iconCls:'detail',
		                    text:'查看',
		                    handler:function()
		                    {
		                       rightClick.hide(true);
		                       var url = "../claim/billSearch.action?sourceType=1&caseId="+recorde.data.caseId; ;
		                       var title = "工单查询";
		                       window.parent.refreshPage("billSearch", title,title, "", url, true);
		                    }
		                };
		         	
		         	<app:isPermission code="/claim/caseAssessView.action">
		         			rightClick.add(detailMenu);
		    		</app:isPermission>
		         	
		         	 if(recorde.data.status == '4'){
			         	 var assessMenu = {
				                    iconCls:'edit',
				                    text:'评估',
				                    handler:function()
				                    {
				                       rightClick.hide(true);
				                       onAssess(rowIndex) ;
				                    }
				                };
		         		
		         		<app:isPermission code="/claim/caseAssessDone.action">
		         				rightClick.add(assessMenu);
	         			</app:isPermission>
		         	 }
		         	 
		         	
		         	/* var copyMenu = {
		         			iconCls:'copy',
		                     text:'复制工单号',
		                     handler:function()
		                     {
		                        rightClick.hide(true);
		                        window.clipboardData.setData("Text",recorde.data.caseId);
		                     }
		                 };
		         	 rightClick.add(copyMenu);*/
			        
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
							        <app:isPermission code="/claim/caseAssessSearch.action">
							        ,new Ext.Button({text:"查询",iconCls:'search',handler:onSearch })
							        </app:isPermission>
							         ],
							layout : 'border',
							items : [ queryForm, gridPanel ]
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
										//anchor:"90%",
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
									//anchor:"90%",
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
									//anchor:"90%",
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
									//anchor:"90%",
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
									//anchor:"90%",
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
									xtype:"textfield",
									//anchor:"90%",
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
									xtype:"combo",
									hiddenName:"caseObject.caseLevel",
									store:new Ext.data.SimpleStore({data: [['1','一级'],['2','二级'],['3','三级'],['4','四级'],['5','五级']], fields: ['key','value']}),
									valueField:"key",
									displayField:"value",
									width:125,
									readOnly:true,
									triggerAction:"all",
									fieldLabel:"案件级别",
									editable:false,
									mode:"local"
									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									//anchor:"90%",
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
									//anchor:"90%",
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
									//anchor:"90%",
									name:"caseObject.reportCompanyTm",
									readOnly:true,
									fieldLabel:"上报保险公司时间"
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
			
			/*************************************/
			var dealGridStore = new Ext.data.JsonStore({
				//autoLoad:true ,
				url : 'processDeal_listProcessDeals.action',
				fields : [ {name : 'id'}, 
				           {name : 'caseId'}, 
				           {name : 'status'}, 
				           {name : 'isQuit'}, 
				           {name : 'quitPeason'},
				           {name : 'dealUser'},
				           {name : 'dealUserName'},
				           {name : 'dealDept'},
				           {name : 'dealContact'},
				           {name : 'dealTime'},
				           {name : 'dealIdea'},
				           {name : 'causeEvent'},
				           {name : 'payTime'},
				           {name : 'fullPay'},
				           {name : 'payReason'}
				           
				],
				root : 'root'
			});
			var checkboxSelectionMode2 = new Ext.grid.CheckboxSelectionModel({});
			var dealGridPanel = new Ext.grid.GridPanel({
				frame:true,
				loadMask:true,
				height:150,
				store : dealGridStore,
				sm : checkboxSelectionMode2,
				enableHdMenu : false,
				cm : new Ext.grid.ColumnModel([// new Ext.grid.RowNumberer(),
						{header : '处理人',width: 70,dataIndex : "dealUserName",	sortable : false},
						{header : '处理部门',width: 180,dataIndex : "dealDept",sortable : false} , 
						{header : '处理时间',width: 100,dataIndex : "dealTime",renderer:	dealTimeRenderer,sortable : false}, 
						{header : '引发其它事件',width: 100,dataIndex : "causeEvent",renderer:causeEventRenderer,sortable : false}, 
						{header : '节点',width: 200,dataIndex : "status",renderer:status2Renderer,sortable : false}
						]),
			listeners:{
				rowdblclick:showDealDetailWin
			}
			});
			
			function dealTimeRenderer(v){
				if(v!=null && v!= ''){
					v = v.split("T")[0] ; 
				}
				return v ;
			}
			
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
			
			var dealDetailForm_atrr_1 = new Ext.form.TextField({width:150,name:"caseId",readOnly:true,fieldLabel:"案件工单号"});
			var dealDetailForm_atrr_2 = new Ext.form.ComboBox({hiddenName:"status",readOnly:true,store:new Ext.data.SimpleStore({data: [['1','新增'],['2','上报'],['3','处理'],['4','完结'],['5','评估']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,triggerAction:"all",fieldLabel:"状态",editable:false,mode:"local"});
			var dealDetailForm_atrr_3 = new Ext.form.TextField({width:150,name:"dealUserName",readOnly:true,fieldLabel:"处理人"});
			var dealDetailForm_atrr_4 = new Ext.form.TextField({width:150,name:"dealDept",readOnly:true,fieldLabel:"处理部门"});
			var dealDetailForm_atrr_5 = new Ext.form.DateField({width:150,name:"dealTime",readOnly:true,format:"Y-m-d",fieldLabel:"处理时间"});
			var dealDetailForm_atrr_6 = new Ext.form.TextField({width:150,name:"dealContact",readOnly:true,fieldLabel:"处理人联系方式"});
			var dealDetailForm_atrr_7 = new Ext.form.ComboBox({hiddenName:"causeEvent",readOnly:true,store:new Ext.data.SimpleStore({data: [['1','未引发其它事件'],['2','劳动争议'],['3','法律诉讼'],['4','外部侵害'],['5','媒体事件'],['6','其它']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,triggerAction:"all",fieldLabel:"引发其它事件",editable:false,mode:"local"});
			var dealDetailForm_atrr_8 = new Ext.form.DateField({width:150,name:"payTime",readOnly:true,format:"Y-m-d H:i",fieldLabel:"付款时间"});
			var dealDetailForm_atrr_9 = new Ext.form.ComboBox({hiddenName:"fullPay",readOnly:true,store:new Ext.data.SimpleStore({data: [['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,triggerAction:"all",fieldLabel:"是否全额赔付",editable:false,mode:"local"});
			var dealDetailForm_atrr_10= new Ext.form.ComboBox({hiddenName:"isQuit",readOnly:true,store:new Ext.data.SimpleStore({data: [['1','是'],['0','否']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,triggerAction:"all",fieldLabel:"是否放弃理赔",editable:false,mode:"local"});
			var dealDetailForm_atrr_11= new Ext.form.TextArea({height:80,width:450,name:"dealIdea",readOnly:true,fieldLabel:"进展情况描述"});
			var dealDetailForm_atrr_12= new Ext.form.TextArea({height:80,width:450,name:"quitPeason",readOnly:true,fieldLabel:"放弃理赔原因"});
			var dealDetailForm_atrr_13= new Ext.form.TextArea({height:80,width:450,name:"payReason",readOnly:true,fieldLabel:"未全额赔付原因"});
			
			var dealDetailForm = new Ext.form.FormPanel({region:"center",height:200,frame:true,autoWidth:true,timeout:300
				,items: [
					new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [dealDetailForm_atrr_1]})
							,new Ext.Panel({width:300,layout:"form",items: [dealDetailForm_atrr_2]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [	dealDetailForm_atrr_3]})
							,new Ext.Panel({width:300,layout:"form",items: [dealDetailForm_atrr_4]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [	dealDetailForm_atrr_5]})
							,new Ext.Panel({width:300,layout:"form",items: [dealDetailForm_atrr_6]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [	dealDetailForm_atrr_7]})
							,new Ext.Panel({width:300,layout:"form",items: [dealDetailForm_atrr_8]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:300,layout:"form",items: [	dealDetailForm_atrr_9]})
							,new Ext.Panel({width:300,layout:"form",items: [dealDetailForm_atrr_10]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:600,layout:"form",items: [dealDetailForm_atrr_11]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:600,layout:"form",items: [dealDetailForm_atrr_12]})
						]
					})
					,new Ext.Panel({layout:"column"
						,items: [
							new Ext.Panel({width:600,layout:"form",items: [dealDetailForm_atrr_13]})
						]
					})
				]

			});
			
			// 处理记录详情
			var dealDetailWin = new Ext.Window({height:440,constrainHeader:true,width:630,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
				,items: [
				         dealDetailForm
				]
			});
			function showDealDetailWin(){
				var records = dealGridPanel.getSelectionModel().getSelections();
				var record = dealGridPanel.getSelectionModel().getSelected();
				if (records.length == 1) {
						dealDetailWin.setTitle("处理信息") ;
						dealDetailWin.show() ;
						dealDetailForm.getForm().reset() ;
						var obj = {};
						for (p in record.data) {
							if(p == 'dealTime'){
								if(record.data[p] != null ){
									obj[p] = record.data[p].split('T')[0] ;
								}else{
									obj[p] = '' ;
								}
								continue ;
							}
							if(p == 'payTime' ){
								if(record.data[p] != null ){
									var time = record.data[p].split("T")[1] ;
									obj[p] = record.data[p].split('T')[0]+" "+time.split(":")[0]+":"+time.split(":")[0] ;;
								}else{
									obj[p] = '' ;
								}
								continue ;
							}
							obj[p] = record.data[p]; 
						}
						dealDetailForm.getForm().setValues(obj);
				}
				
			}
			
			var hisDealFieldSet = new Ext.form.FieldSet({
				labelWidth : 100,
				autoHeight:true,
				title : "历史处理记录",
				layout : 'form',
				items : [
				         dealGridPanel	
				         ]
				})
			
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
			
			
			var dealForm = new Ext.form.FormPanel({
				autoHeight : true,
				width:764,
				collapsible:true,
				title:"处理信息",
				items : [
				      hisDealFieldSet
				]
			});
			/****************案件评估*****************/
			var assessFieldSet = new Ext.form.FieldSet({
				//labelWidth : 100,
				autoHeight:true,
				title:"案件评估",
				layout : 'column',
				items : [{
							xtype : "panel",
							labelAlign : "left",
							columnWidth : 1,
							layout : "form",
							items : [{
										xtype: 'label',
										html: '1.收到报案时，保险经纪公司是否能够及时提供处理建议，便于索赔得以实现？'
									}]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "left",
							layout : "form",
							items : [
							         {
							        	id:"point1_id",
										xtype: 'radiogroup',
										itemCls: 'x-check-group-alt',
										columns: 1,
										autoWidth:true,
										allowBlank:false,
										items: [
										        {boxLabel: "是", name: 'processAssess.point1', inputValue: 1},
										        {boxLabel: "否", name: 'processAssess.point1', inputValue: 2}
										    ]
										}
									]
						},
						{
							xtype : "panel",
							labelAlign : "left",
							columnWidth : 1,
							layout : "form",
							items : [{
										xtype: 'label',
										html: '2.理赔资料收集时，保险经纪公司是否给予建议，便于案件顺利获得赔付？'
									}]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "left",
							layout : "form",
							items : [
							         {
							        	id:"point2_id",
										xtype: 'radiogroup',
										itemCls: 'x-check-group-alt',
										columns: 1,
										autoWidth:true,
										allowBlank:false,
										items: [
										        {boxLabel: "是", name: 'processAssess.point2', inputValue: 1},
										        {boxLabel: "否", name: 'processAssess.point2', inputValue: 2}
										    ]
										}
									]
						},
						{
							xtype : "panel",
							labelAlign : "left",
							columnWidth : 1,
							layout : "form",
							items : [{
										xtype: 'label',
										html: '3.理赔过程中，保险经纪公司是否能够及时告知案件进度？'
									}]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "left",
							layout : "form",
							items : [
							         {
							        	id:"point3_id",
										xtype: 'radiogroup',
										itemCls: 'x-check-group-alt',
										columns: 1,
										autoWidth:true,
										allowBlank:false,
										items: [
										        {boxLabel: "是", name: 'processAssess.point3', inputValue: 1},
										        {boxLabel: "否", name: 'processAssess.point3', inputValue: 2}
										    ]
										}
									]
						},
						{
							xtype : "panel",
							labelAlign : "left",
							columnWidth : 1,
							layout : "form",
							items : [{
										xtype: 'label',
										html: '4.对理赔有异议时，保险经纪公司是否给予合适的解释说明？'
									}]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "left",
							layout : "form",
							items : [
							         {
							        	id:"point4_id",
										xtype: 'radiogroup',
										itemCls: 'x-check-group-alt',
										columns: 1,
										autoWidth:true,
										allowBlank:false,
										items: [
										        {boxLabel: "是", name: 'processAssess.point4', inputValue: 1},
										        {boxLabel: "否", name: 'processAssess.point4', inputValue: 2}
										    ]
										}
									]
						},
						{
							xtype : "panel",
							labelAlign : "left",
							columnWidth : 1,
							layout : "form",
							items : [{
										xtype: 'label',
										html: '5.您对此次理赔结果是否满意？'
									}]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "left",
							layout : "form",
							items : [
							         {
							        	id:"point5_id",
										xtype: 'radiogroup',
										itemCls: 'x-check-group-alt',
										columns: 1,
										autoWidth:true,
										allowBlank:false,
										items: [
										        {boxLabel: "是", name: 'processAssess.point5', inputValue: 1},
										        {boxLabel: "否", name: 'processAssess.point5', inputValue: 2}
										    ],
									    listeners:{
									    	change:function(o){
									    		var inputValue = o.getValue().getRawValue() ;
									    		if(inputValue == 2){
									    			assessForm.getForm().findField("processAssess.point5Desc").show();
									    		}else if(inputValue == 1){
									    			assessForm.getForm().findField("processAssess.point5Desc").hide();
									    		}
									    	}
									    }
										}
									]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "right",
							layout : "form",
							items : [{
									xtype:'textarea',
									height:80,
									width:600,
									hidden:true ,
									name:"processAssess.point5Desc",
									fieldLabel:"请说明原因"
									}]
						},
						{
							xtype : "panel",
							labelAlign : "left",
							columnWidth : 1,
							layout : "form",
							items : [{
										xtype: 'label',
										html: '6.您对此次案件理赔的时效性是否满意？'
									}]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "left",
							layout : "form",
							items : [
							         {
							        	id:"point6_id",
										xtype: 'radiogroup',
										itemCls: 'x-check-group-alt',
										columns: 1,
										autoWidth:true,
										allowBlank:false,
										items: [
										        {boxLabel: "是", name: 'processAssess.point6', inputValue: 1},
										        {boxLabel: "否", name: 'processAssess.point6', inputValue: 2}
										    ]
										}
									]
						}
						,
						{
							xtype : "panel",
							labelAlign : "left",
							columnWidth : 1,
							layout : "form",
							items : [{
										xtype: 'label',
										html: '7.保险经纪公司的服务态度如何？'
									}]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "left",
							layout : "form",
							items : [
							         {
							        	id:"point7_id",
										xtype: 'radiogroup',
										itemCls: 'x-check-group-alt',
										columns: 1,
										autoWidth:true,
										allowBlank:false,
										items: [
										        {boxLabel: "礼貌、热情且专业", name: 'processAssess.point7', inputValue: 1},
										        {boxLabel: "不够专业", name: 'processAssess.point7', inputValue: 2},
										        {boxLabel: "不礼貌", name: 'processAssess.point7', inputValue: 3}
										    ],
									    listeners:{
									    	change:function(o){
									    		var inputValue = o.getValue().getRawValue() ;
									    		if(inputValue == 2 || inputValue == 3){
									    			assessForm.getForm().findField("processAssess.point7Desc").show();
									    		}else if(inputValue == 1){
									    			assessForm.getForm().findField("processAssess.point7Desc").hide();
									    		}
									    	}
									    }
										}
									]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "right",
							layout : "form",
							items : [{
									xtype:'textarea',
									height:80,
									width:600,
									hidden:true ,
									name:"processAssess.point7Desc",
									fieldLabel:"请举例"
									}]
						},
						{
							xtype : "panel",
							labelAlign : "left",
							columnWidth : 1,
							layout : "form",
							items : [{
										xtype: 'label',
										html: '8.请对此次保险经纪公司所提供的服务给予总体评价'
									}]
						},
						{
							xtype : "panel",
							columnWidth : 1,
							labelAlign : "left",
							layout : "form",
							items : [
							         {
							        	id:"point8_id",
										xtype: 'radiogroup',
										itemCls: 'x-check-group-alt',
										columns: 1,
										autoWidth:true,
										allowBlank:false,
										items: [
										        {boxLabel: "90-100", name: 'processAssess.point8', inputValue: 1},
										        {boxLabel: "80-90", name: 'processAssess.point8', inputValue: 2},
										        {boxLabel: "70-80", name: 'processAssess.point8', inputValue: 3},
										        {boxLabel: "60-70", name: 'processAssess.point8', inputValue: 4},
										        {boxLabel: "60以下", name: 'processAssess.point8', inputValue: 5}
										    ]
										},{
											xtype:"hidden",
											name:"processAssess.caseId"
										}
									]
						}
		         ]
				});
			var assessForm = new Ext.form.FormPanel({
				autoHeight : true,
				width:764,
				collapsible:true,
				title:"评估信息",
				items : [
				      assessFieldSet
				]
			});
			/**************************************/
			var btnSave = new Ext.Button({text:"保存",iconCls:'save',handler:onSave });
			//案件评估窗口
			var configWindow = new Ext.Window({
				modal:true,plain:true,
				resizable:false,closeAction:"hide",
				layout:"fit",
				width:810,closable:true,height:500,title:"案件评估",
				tbar: [
						btnSave
				],
				items: [{
					xtype:"panel",
					autoScroll:true,
					frame:true,
					items:[{
						//基础数据
						xtype:'panel',
						autoHeight:true,
						items:[baseForm]
					},
					{
						autoHeight:true,
						xtype:'panel',
						id:"personalTplPanel",
						items:[]
					},
					{
						autoHeight:true,
						xtype:'panel',
						items:[dealForm]
					},
					{
						autoHeight:true,
						xtype:'panel',
						items:[assessForm]
					}]
				}]
			});
		
			//保存处理信息
			function onSave(){
				/*if (assessForm.getForm().isValid() == false) {
					Ext.MessageBox.alert("提示","数据校验错误！");
					return ;
				} */
				var point1 = Ext.getCmp('point1_id').getValue() ;
				var point2 = Ext.getCmp('point2_id').getValue() ;
				var point3 = Ext.getCmp('point3_id').getValue() ;
				var point4 = Ext.getCmp('point4_id').getValue() ;
				var point5 = Ext.getCmp('point5_id').getValue() ;
				var point6 = Ext.getCmp('point6_id').getValue() ;
				var point7 = Ext.getCmp('point7_id').getValue() ;
				var point8 = Ext.getCmp('point8_id').getValue() ;
				if(point1== null||point2== null||point3== null||point4== null||point5== null||point6== null||point7== null||point8== null){
					Ext.MessageBox.alert("提示","数据校验错误，请确保所有项都已正确填写");
					return ;
				}
				/******************************/
				var point5 = Ext.getCmp('point5_id').getValue().getRawValue() ;
				var point5Desc =  assessForm.getForm().findField("processAssess.point5Desc").getValue() ;
				if(point5 == 2){
					if(point5Desc == ''){
						Ext.MessageBox.alert("提示","请填写您对此次理赔结果不满意的原因！");
						return ;
					}
				}
				var point7 = Ext.getCmp('point7_id').getValue().getRawValue() ;
				var point7Desc =  assessForm.getForm().findField("processAssess.point7Desc").getValue() ;
				if(point7 == 2 || point7 == 3){
					if(point7Desc == ''){
						Ext.MessageBox.alert("提示","请您对保险经纪公司的服务态度不够专业或不礼貌的情况举例说明！");
						return ;
					}
				}
				var caseId = baseForm.getForm().findField("caseObject.caseId").getValue() ;
				assessForm.getForm().findField("processAssess.caseId").setValue(caseId);
				assessForm.getForm().submit(
						{
							url:"processAssess_save.action",
							success : showSaveOrUpdateSuccessInfo,
							failure : showSaveOrUpdateFailureInfo,
							waitMsg : "正在执行评估操作...",
							waitTitle : "请稍后..."
						});
				
			}
			function showSaveOrUpdateSuccessInfo(form, action) {
				if (action.result.msg) {
					Ext.Msg.alert("提示",action.result.msg);
					configWindow.hide();
					gridStore.reload() ;
				} else {
					Ext.MessageBox.alert("提示","操作成功");
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
			
			//案件评估
			function onAssess(rowIndex){
				var  record = gridStore.getAt(rowIndex);
				//根据案件类型更换个性化模板
				var insureTypeId = record.data.insuretypeId ;
				var personalForm ;
				if(insureTypeId == '5'){//雇主责任险模板
					personalForm = new SF.claim.assess.PersonalHirerForm();
				}else if(insureTypeId == '6'){ //员工意外险模板
					personalForm = new SF.claim.assess.PersonalEmpForm();
				}
				var personalTplPanel = Ext.getCmp("personalTplPanel");
				personalTplPanel.removeAll();
				personalTplPanel.insert(0,personalForm);
				
				configWindow.setTitle("案件评估");
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
				/************************/
				//加载处理信息
				dealGridStore.baseParams["processDeal.caseId"] = record.data.caseId;
				dealGridStore.load();
				/*************************/
				//个性化数据
				var insureTypeurl ;
				
				if(insureTypeId == '5'){//雇主责任险模板
					setPersonalHirerData(personalForm,record.data.caseId) ;
				}else if(insureTypeId == '6'){ //员工意外险模板
					setPersonalEmpData(personalForm,record.data.caseId) ;
				}
				
				
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
									obj[p] = Object[p].split("T")[0] ;
									continue ;
								}
								obj[p] = Object[p]; 
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
									obj[p] = Object[p].split("T")[0] ;
									continue ;
								}
								obj[p] = Object[p]; 
							}
							personalForm.getForm().setValues(obj);
						} else {
						}
					},
					failure:ajaxRequestFailure
				});
			}
			
		});