<%@ page language="java" contentType="text/html; charset=utf-8"%>

function downloadFile(v){
	window.location ='processAdditionDownload.action?processAddition.id='+v ;
}

function deleteFile(v){
	Ext.Ajax.request({params: {"processAddition.id": v},
		url: "processAddition_delete.action",
		success: function(response) {
			var resp = Ext.util.JSON.decode(response.responseText);
			if (resp.success) {
				Ext.MessageBox.alert('提示','附件已删除');
				Ext.getCmp("dealUploadGridPanel").getStore().reload();
			} else {
				Ext.MessageBox.alert('操作失败', resp.msg);
			}
		}
	});
}


Ext.onReady(function() {
			var gridStore = new Ext.data.JsonStore({
				url : 'case_listPageBy.action?caseObject.queryType=deal',
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

			var queryForm_atrr_3 = new Ext.form.ComboBox({hiddenName:"caseObject.status",store:new Ext.data.SimpleStore({data: [['','全部'],['2','上报'],['3','处理']], fields: ['key','value']}),valueField:"key",displayField:"value",width:125,triggerAction:"all",emptyText : '全部',fieldLabel:"状态",editable:false,mode:"local"});

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
				    <app:isPermission code="/claim/caseDealView.action">
				    		rightClick.add(detailMenu);
			        </app:isPermission>
		         	
		         	 
	         		 var dealMenu = {
			                    iconCls:'edit',
			                    text:'处理',
			                    handler:function()
			                    {
			                       rightClick.hide(true);
			                       onDeal(rowIndex) ;
			                    }
			                };
	         		 
	         		<app:isPermission code="/claim/caseDealDone.action">
	         		 		rightClick.add(dealMenu);
			        </app:isPermission>
		         	
		         	
		         	var uploadMenu = {
		                    iconCls:'goto',
		                    text:'理赔资料上传',
		                    handler:function()
		                    {
		                       rightClick.hide(true);
		                       onUpload(rowIndex) ;
		                    }
		                };
		         	<app:isPermission code="/claim/caseDealAddition.action">
		         			rightClick.add(uploadMenu);
     		 		</app:isPermission>
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
			var currntUser ;
			new Ext.Viewport({
				layout : 'border',
				items : [
				         treePanel,
						{
							region : 'center',
							tbar : [ '-'
							        <app:isPermission code="/claim/caseDealSearch.action">
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
							}//end afterrender
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
				labelWidth : 110,
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
										name:"caseId",
										readOnly:true,
										allowBlank:false,
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
									name:"reportDeptName",
									readOnly:true,
									allowBlank:false,
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
									name:"reportUserName",
									readOnly:true,
									allowBlank:false,
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
									name:"reportContact",
									readOnly:true,
									allowBlank:false,
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
									name:"insuretypeName",
									readOnly:true,
									allowBlank:false,
									fieldLabel:"案件类型"
									},{
										xtype:"hidden",
										name:"insuretypeId"
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
									name:"caseOccourTime",
									readOnly:true,
									allowBlank:false,
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
									//anchor:"90%",
									name:"caseFindTime",
									readOnly:true,
									allowBlank:false,
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
									name:"casePlace",
									readOnly:true,
									allowBlank:false,
									fieldLabel:"发生地点"
									}]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"combo",
									hiddenName:"caseLevel",
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
							columnWidth : 1,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"datefield",
									//anchor:"90%",
									name:"reportCompanyTm",
									format:"Y-m-d",
									width:125,
									allowBlank:false,
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
									name:"caseDesc",
									readOnly:true,
									allowBlank:false,
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
			/****************deal form*******************/
			
			var dealFieldSet = new Ext.form.FieldSet({
				labelWidth : 100,
				autoHeight:true,
				//height:300,
				title : "处理数据",
				layout : 'column',
				items : [
								{
									xtype : "panel",
									columnWidth : .33,
									labelAlign : "left",
									layout : "form",
									items : [{
										xtype:"combo",
										hiddenName:"processDeal.status",
										store:new Ext.data.SimpleStore({data: [['3','处理'],['4','完结']], fields: ['key','value']}),
										valueField:"key",
										allowBlank:false,
										displayField:"value",
										width:125,
										triggerAction:"all",
										fieldLabel:"理赔状态",
										editable:false,
										mode:"local",
										labelSeparator:'<font color=red>*</font>',
										listeners:{
											select:function(combox,record,index){
												if(combox.getRawValue() == '完结'){

													dealForm.getForm().findField("processDeal.isQuit").enable() ;
													dealForm.getForm().findField("processDeal.isQuit").setValue('') ;
													dealForm.getForm().findField("processDeal.quitPeason").enable() ;
													
													var isQuit = dealForm.getForm().findField("processDeal.isQuit").getValue();
													if(isQuit == '1'){
														
														dealForm.getForm().findField("processDeal.causeEvent").disable() ;
														dealForm.getForm().findField("processDeal.dealContact").disable() ;
														dealForm.getForm().findField("processDeal.payTime").disable() ;
														dealForm.getForm().findField("processDeal.fullPay").disable() ;
														dealForm.getForm().findField("processDeal.dealIdea").disable() ;
														dealForm.getForm().findField("processDeal.payReason").disable() ;
														
														dealForm.getForm().findField("processDeal.causeEvent").setValue('') ;
														dealForm.getForm().findField("processDeal.dealContact").setValue('') ;
														dealForm.getForm().findField("processDeal.payTime").setValue('') ;
														dealForm.getForm().findField("processDeal.fullPay").setValue('') ;
														dealForm.getForm().findField("processDeal.dealIdea").setValue('') ;
														dealForm.getForm().findField("processDeal.payReason").setValue('') ;
														
													}

												}else{
													dealForm.getForm().findField("processDeal.causeEvent").enable() ;
													dealForm.getForm().findField("processDeal.dealContact").enable() ;
													dealForm.getForm().findField("processDeal.payTime").enable() ;
													dealForm.getForm().findField("processDeal.fullPay").enable() ;
													dealForm.getForm().findField("processDeal.dealIdea").enable() ;
													dealForm.getForm().findField("processDeal.payReason").enable() ;
													
													dealForm.getForm().findField("processDeal.isQuit").setValue('0') ;
													dealForm.getForm().findField("processDeal.isQuit").disable() ;
													dealForm.getForm().findField("processDeal.quitPeason").setValue('') ;
													dealForm.getForm().findField("processDeal.quitPeason").disable() ;
													
												}
											}
										}
									}]
								},
								{
									xtype : "panel",
									columnWidth : .33,
									labelAlign : "left",
									layout : "form",
									items : [{
										xtype:"combo",
										hiddenName:"processDeal.isQuit",
										store:new Ext.data.SimpleStore({
											data: [['1','是'],['0','否']], 
											fields: ['key','value']
										}),
										valueField:"key",
										displayField:"value",
										value:"0",
										width:125,
										triggerAction:"all",
										fieldLabel:"是否放弃理赔",
										editable:false,
										mode:"local",
										listeners:{
											select:function(combox,record,index){
												if(combox.getRawValue() == '是'){
													var status = dealForm.getForm().findField("processDeal.status").getValue();
													if(status == '4'){//完结
														dealForm.getForm().findField("processDeal.causeEvent").disable() ;
														dealForm.getForm().findField("processDeal.dealContact").disable() ;
														dealForm.getForm().findField("processDeal.payTime").disable() ;
														dealForm.getForm().findField("processDeal.fullPay").disable() ;
														dealForm.getForm().findField("processDeal.dealIdea").disable() ;
														dealForm.getForm().findField("processDeal.payReason").disable() ;
														
														dealForm.getForm().findField("processDeal.causeEvent").setValue('') ;
														dealForm.getForm().findField("processDeal.dealContact").setValue('') ;
														dealForm.getForm().findField("processDeal.payTime").setValue('') ;
														dealForm.getForm().findField("processDeal.fullPay").setValue('') ;
														dealForm.getForm().findField("processDeal.dealIdea").setValue('') ;
														dealForm.getForm().findField("processDeal.payReason").setValue('') ;
													}
												}else{
													dealForm.getForm().findField("processDeal.causeEvent").enable() ;
													dealForm.getForm().findField("processDeal.dealContact").enable() ;
													dealForm.getForm().findField("processDeal.payTime").enable() ;
													dealForm.getForm().findField("processDeal.fullPay").enable() ;
													dealForm.getForm().findField("processDeal.dealIdea").enable() ;
													dealForm.getForm().findField("processDeal.payReason").enable() ;
												}
												
											}
										}
									}]
								},
								{
									xtype : "panel",
									columnWidth : .33,
									labelAlign : "left",
									layout : "form",
									items : [{
										xtype:"combo",
										hiddenName:"processDeal.causeEvent",
										store:new Ext.data.SimpleStore({data: [['1','未引发其它事件'],['2','劳动争议'],['3','法律诉讼'],['4','外部侵害'],['5','媒体事件'],['6','其它']], fields: ['key','value']}),
										valueField:"key",
										displayField:"value",
										width:125,
										triggerAction:"all",
										fieldLabel:"引发其它事件",
										allowBlank:false,
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
										width:125,
										maxLength :60, 
										name:"processDeal.dealContact",
										allowBlank:false,
										fieldLabel:"联系方式"
									}]
								},
								{
									xtype : "panel",
									columnWidth : .33,
									labelAlign : "left",
									layout : "form",
									items : [{
										xtype:"datefield",
										width:125,
										name:"processDeal.payTime",
										format:"Y-m-d H:i",
										fieldLabel:"付款时间"
									}]
								},
								{
									xtype : "panel",
									columnWidth : .33,
									labelAlign : "left",
									layout : "form",
									items : [{
										xtype:"combo",
										hiddenName:"processDeal.fullPay",
										store:new Ext.data.SimpleStore({data: [['1','是'],['0','否']], fields: ['key','value']}),
										valueField:"key",
										displayField:"value",
										width:125,
										triggerAction:"all",
										fieldLabel:"是否全额赔付",
										editable:false,
										mode:"local"
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
										name:"processDeal.dealIdea",
										maxLength :1300, 
										fieldLabel:"进展情况描述",
										allowBlank:false
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
										name:"processDeal.quitPeason",
										maxLength :1300,
										fieldLabel:"放弃理赔原因"
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
										name:"processDeal.payReason",
										maxLength :1300,
										fieldLabel:"未全额赔付原因"
									},{
										xtype:"hidden",
										name:"processDeal.caseId"
									}]
								}
				         ]
				});
			
			var baseForm = new Ext.form.FormPanel({
				frame:true,
				autoHeight : true,
				width:790,
				collapsible:true,
				title:"基础数据",
				items : [
				    baseFieldSet
				]
			});
			
			
			var dealForm = new Ext.form.FormPanel({
				autoHeight : true,
				frame:true,
				width:790,
				collapsible:true,
				title:"处理信息",
				items : [
				      hisDealFieldSet
			         ,dealFieldSet
				]
			});
			
			/*****************理赔资料上传***********************/
			function onUpload(rowIndex){
				var  record = gridStore.getAt(rowIndex);
				uploadWin.setTitle("理赔资料上传");
				uploadWin.show() ;
				uploadForm.getForm().reset() ;
				uploadForm.getForm().findField("processAddition.caseId").setValue(record.data.caseId);
				dealUploadGridStore.baseParams["processAddition.caseId"] = record.data.caseId;
				dealUploadGridStore.load() ;
			}
			
			var uploadForm = new Ext.form.FormPanel({
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
								items : [{
									xtype : 'textfield',
									inputType : "file",
									allowBlank:false,
									name : "uploadFile",
									fieldLabel : "选择文件"
								},{
									xtype : 'hidden',
									name:"processAddition.caseId"
								},{
									xtype : 'hidden',
									name:"uploadFileName"
								}]
							}
					]
				}]
			});
			var dealUploadGridStore = new Ext.data.JsonStore({
				//autoLoad:true ,
				url : 'processAddition_listProcessAdditions.action',
				fields : [ {name : 'id'}, 
				           {name : 'caseId'}, 
				           {name : 'name'}, 
				           {name : 'path'}
				],
				root : 'root'
			});
			var checkboxSelectionMode3 = new Ext.grid.CheckboxSelectionModel({});
			var dealUploadGridPanel = new Ext.grid.GridPanel({
				id:"dealUploadGridPanel",
				frame:true,
				loadMask:true,
				height:200,
				store : dealUploadGridStore,
				sm : checkboxSelectionMode3,
				enableHdMenu : false,
				cm : new Ext.grid.ColumnModel([ new Ext.grid.RowNumberer(),
						{header : '附件',width: 260,dataIndex : "name",	sortable : false},
						{header : '操作',width: 260,dataIndex : "id",renderer:operationRenderer,sortable : false} 
						])
			});
			function operationRenderer(v){
				return "<img src=\"../images/claim/download.gif\" alt='下载' onclick='javascript:downloadFile("+v+");'/>&nbsp;&nbsp;&nbsp;&nbsp;<img src=\"../images/claim/close.gif\" alt='删除' onclick='javascript:deleteFile("+v+");'/>"
				
			}
			
			var uploadWin = new Ext.Window({
				autoHeight:true,
				constrainHeader : true,
				width : 600,
				closeAction : "hide",
				resizable : false,
				isEditing : false,
				plain : true,
				modal : true,
				listeners:{
					 hide:function(){
					 	uploadForm.getForm().reset();
					 }
					},
				tbar : [new Ext.Button({
							icon : "../images/save.gif",
							text : "保存",
							cls : "x-btn-text-icon",
							handler : function() {
								if (uploadForm.getForm().isValid() == false) {
									Ext.MessageBox.alert("提示","请选择上传文件！");
									return;
								}
								var filename = uploadForm.getForm().findField("uploadFile").getValue();
								uploadForm.getForm().findField("uploadFileName").setValue(filename) ;
								uploadForm.getForm().submit({
									url : "processAdditionUpload.action",
									fileUpload : true,
									success : function(form, action) {
										if (action.result.msg) {
											Ext.Msg.alert("提示",action.result.msg);
										}else{
											Ext.MessageBox.alert("提示","上传成功");
											dealUploadGridStore.reload();
											uploadForm.getForm().findField("uploadFile").setValue('');
										}
										
									},
									failure :function(form,action){
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
											if(msg == ''){
												msg = '上传失败，文件大小不能超过5M，如果文件过大，请压缩后再上传' ;
											}
											Ext.Msg.alert("提示", msg);
										}
										
									} ,
									waitMsg : "正在上传..."
								});
							}
						})],
				items :  [uploadForm,dealUploadGridPanel]
			});
			
			/**************************************/
			var btnSave = new Ext.Button({text:"保存",iconCls:'save',handler:onSave });
			//案件处理窗口
			var configWindow = new Ext.Window({
				modal:true,plain:true,
				resizable:false,closeAction:"hide",
				layout:"fit",
				width:840,closable:true,height:500,title:"案件处理",
				tbar: [
						btnSave
				],
				items: [{
					xtype:"panel",
					autoScroll:true,
					frame:true,
					items:[
						       {
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
								}
							]
				}]
			});
		
			//保存处理信息
			function onSave(){
				if (baseForm.getForm().isValid() == false) {
					Ext.MessageBox.alert("提示","数据校验错误！");
					return ;
				} 
				var personForm = Ext.getCmp("personalTplPanel").items.get(0);
				if (personForm.getForm().isValid() == false) {
					Ext.MessageBox.alert("提示","数据校验错误！");
					return ;
				} 
				//状态
				var status = dealForm.getForm().findField("processDeal.status").getValue();
				//个性化数据校验
				var insuretypeId = baseForm.getForm().findField("insuretypeId").getValue() ;
				if(insuretypeId == '5'){//雇主责任险模板
					if(status == '4'){
						var billFee = personForm.getForm().findField('billFee').getValue() ;
						var totalFee = personForm.getForm().findField('totalFee').getValue() ;
						if(billFee == ''){
							Ext.MessageBox.alert("提示","完结时，票据金额必填！");
							return ;
						}
						if(totalFee == ''){
							Ext.MessageBox.alert("提示","完结时，总赔付金额必填！");
							return ;
						}
					}
				}else if(insuretypeId == '6'){//员工意外险
					if(status == '4'){
						var billFee = personForm.getForm().findField('billFee').getValue() ;
						var totalFee = personForm.getForm().findField('totalFee').getValue() ;
						if(billFee == ''){
							Ext.MessageBox.alert("提示","完结时，票据金额必填！");
							return ;
						}
						if(totalFee == ''){
							Ext.MessageBox.alert("提示","完结时，总赔付金额必填！");
							return ;
						}
					}
				}
				
				if (dealForm.getForm().isValid() == false) {
					Ext.MessageBox.alert("提示","数据校验错误！");
					return ;
				} 
				
				//是否放弃理赔
				var isQuit = dealForm.getForm().findField("processDeal.isQuit").getValue();
				//放弃理赔原因
				var quitPeason = dealForm.getForm().findField("processDeal.quitPeason").getValue();
				//付款时间
				var payTime = dealForm.getForm().findField("processDeal.payTime").getValue();
				//是否全额赔付:0=否1=是
				var fullPay = dealForm.getForm().findField("processDeal.fullPay").getValue();
				//未全额赔付原因
				var payReason = dealForm.getForm().findField("processDeal.payReason").getValue();
				//完结时
				if(status == '4'){
					if(isQuit == '0'){
						if(payTime == ''){
							Ext.MessageBox.alert("提示","完结时，付款时间项必填！");
							return ;
						}
						//付款时间不能大于当前时间
						if(payTime > new Date()){
							Ext.MessageBox.alert("提示","付款时间不能大于当前日期！");
							return ;
						}
						if(fullPay == ''){
							Ext.MessageBox.alert("提示","完结时，是否全额赔付项必填！");
							return ;
						}
					}
					
				}
				if(isQuit == '1'){
					if(quitPeason == ''){
						Ext.MessageBox.alert("提示","请填写放弃理赔原因！");
						return ;
					}
				}else{
					if(fullPay == '0'){
						if(payReason == ''){
							Ext.MessageBox.alert("提示","请填写未全额赔付原因！");
							return ;
						}
					}
				}
				//基础数据表单参数
				var baseData = new Array() ;
				baseData.push(baseForm.getForm().getValues());
				var baseParam = Ext.util.JSON.encode(baseData);
				//个性化数据表单数据
				var personData = new Array() ;
				personData.push(personForm.getForm().getValues());
				var persaonParam = Ext.util.JSON.encode(personData);
				//提交表单
				var caseId = baseForm.getForm().findField("caseId").getValue() ;
				dealForm.getForm().findField("processDeal.caseId").setValue(caseId);
				dealForm.getForm().submit(
						{
							params:{"baseData":baseParam,"personData":persaonParam},
							url:"processDeal_save.action",
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
					gridStore.reload();
					dealGridStore.reload();
					dealForm.getForm().reset() ;
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
			
			//案件处理
			function onDeal(rowIndex){
				var  record = gridStore.getAt(rowIndex);
				//根据案件类型更换个性化模板
				var insureTypeId = record.data.insuretypeId ;
				var personalForm ;
				if(insureTypeId == '5'){//雇主责任险模板
					personalForm = new SF.claim.deal.PersonalHirerForm();
				}else if(insureTypeId == '6'){ //员工意外险模板
					personalForm = new SF.claim.deal.PersonalEmpForm();
				}
				var personalTplPanel = Ext.getCmp("personalTplPanel");
				personalTplPanel.removeAll();
				personalTplPanel.insert(0,personalForm);
				
				configWindow.setTitle("案件处理");
				configWindow.show();
				baseForm.getForm().reset() ;
				/**************************/
				//填充基础信息数据
				var baseObj = {};
				for (var p in record.data) {
					if(p == 'caseOccourTime' || p == 'caseFindTime'){
						var time = record.data[p].split("T")[1] ;
						baseObj[p] = record.data[p].split("T")[0]+" "+time.split(":")[0]+":"+time.split(":")[0] ;
						continue ;
					}
					if(p == 'reportCompanyTm' && record.data[p] != null){
						baseObj[p] = record.data[p].split("T")[0] ;
						continue ;
					}
					baseObj[p] = record.data[p]; 
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