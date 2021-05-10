Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		autoLoad:true,
		url:"listAll",
		fields:[{name: "id"},
		        {name:"roleName"},
		        {name:"desc"}
		        ],
		root:"root"
	});
	
	var editForm = new Ext.form.FormPanel({
		frame:true,
		region:"north",
		autoHeight:true,
		items: [

			new Ext.form.FieldSet({
				labelWidth:80,
				autoHeight:true,
				layout:"column",
				items:[{
							xtype : "panel",
							columnWidth : 1,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [{
										xtype:"textfield",
										width:150,
										allowBlank:false,
										name:"roleName",
										fieldLabel:"角色名称"
									},{
										xtype:"hidden",
										name:"id"
									}]
						},{
							xtype : "panel",
							columnWidth : 1,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [{
										xtype:"textarea",
										height:80,
										width:300,
										name:"desc",
										fieldLabel:"定义"
									}]
						}
				     ]
				})
	]});
	
	var pagingBar = new Ext.PagingToolbar({
		displayInfo:true,
		displayMsg:"当前显示 {0} - {1} 总记录数目 {2}",
		store:gridStore,
		pageSize:pageSize,
		emptyMsg:"未检索到数据"});
	var checkboxSelectionMode  = new Ext.grid.CheckboxSelectionModel({singleSelect:true});
	var gridPanel = new Ext.grid.GridPanel(
			{
			region:"center",
			autoScroll:"true",
			//tbar:pagingBar,
			store:gridStore,
			sm: checkboxSelectionMode,
			//stripeRows : true,
			cm: new Ext.grid.ColumnModel([
			                             // checkboxSelectionMode,
			                              {header:"角色列表",width:200,dataIndex:"roleName"}
			                              ]),
			listeners:{
				click :function(event){
					onSetRoleInfo();
				}
			}
			
	});
	var treeLoader = new Ext.tree.TreeLoader({
		autoLoad:true,
		url : "findModulesByRoleId",
		baseAttrs:{uiProvider :Ext.tree.TreeCheckNodeUI},
		listeners:{
		    beforeload:function(treeLoader, node){
		     //this.baseParams.pid = node.attributes.id;
		     var roleId = editForm.getForm().findField("id").getValue() ;
		     this.baseParams.roleId = roleId;
		    }
		}
	});
	var rootNode = new Ext.tree.AsyncTreeNode({
		id : "0"
	});
	var tabs = new Ext.TabPanel({
		region:"center",
	    activeTab: 0,
	    items: [{
			    	xtype:"panel",
			    	layout:"border",
			        title: '权限',
			        items:[{
			        	  	xtype:"treepanel",
			        	  	id:"moduleTree",
			        	  	region:"center",
							autoScroll : true,
							split : true,
							width : 250,
							rootVisible: false,
							checkModel:"cascade",
							onlyLeafCheckable:false,
							loader: treeLoader,
					        root: rootNode
			        	  	
			        }]
			    }]
	});
	
	var btnAdd = new Ext.Button({text:"新增",iconCls:'add',handler:onAdd }) ;
	var btnEdit = new Ext.Button({text:"保存",iconCls:'save',handler:onUpdate }) ;
	var btnDelete = new Ext.Button({text:"删除",iconCls:'delete',handler:onDelete }) ;
	
	new Ext.Viewport({
		layout:"border",
		items:[{
				region:"west",
				width:"200",
				layout:"border",
				items:[
				       gridPanel
				       ]
				},{
				region:"center",
				tbar:[btnAdd,"-",btnEdit,"-",btnDelete],
				layout:"border",
				//bodyBorder:false,
				items:[
				       editForm,
				       tabs
				       ]
				}]
	});
	
	var addForm = new Ext.form.FormPanel({region:"center",height:200,frame:true,fileUpload:false,autoWidth:true,timeout:300
		,items: [{
					xtype : "panel",
					columnWidth : 1,
					labelWidth : 120,
					labelAlign : "left",
					layout : "form",
					items : [{
								xtype:"textfield",
								width:150,
								allowBlank:false,
								name:"roleName",
								fieldLabel:"角色名称"
							}]
				},{
					xtype : "panel",
					columnWidth : 1,
					labelWidth : 120,
					labelAlign : "left",
					layout : "form",
					items : [{
								xtype:"textarea",
								height:80,
								width:300,
								name:"desc",
								fieldLabel:"定义"
							}]
				}]
	});
	var btnSave = new Ext.Button({text:"保存",iconCls:'save',handler:onSave}) ;
	// 新增窗口
	var addWin = new Ext.Window({height:250,constrainHeader:true,width:450,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
		,tbar: [
		        btnSave
		]
		,items: [
		         addForm
		]
	});
	//新增
	function onAdd(){
		addWin.setTitle("新增");
		addWin.show();
		addForm.getForm().reset();
	}
	// 设置角色信息数据
	function onSetRoleInfo() {
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			var obj = {};
			for (p in record.data) {
				obj[p] = record.data[p]; 
			}
			
			editForm.getForm().setValues(obj);
			var tree = Ext.getCmp("moduleTree");
			tree.root.reload() ;
			tree.expandAll() ;
			
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时修改多条数据");
		}
	}
	function onUpdate(){
		if (editForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("提示","数据校验错误！");
			return;
		}
		var ids = Ext.getCmp("moduleTree").getChecked("id");
		editForm.getForm().url = "update";
		editForm.getForm().submit(
						{
							params:{moduleIds:ids},
							success : function(form, action){
								if (action.result.msg) {
									Ext.Msg.alert("提示",action.result.msg);
								} else {
									Ext.Msg.alert("提示","修改成功");
									gridStore.reload();
								}
							},
							failure : showFormSubmitFailureInfo,
							waitMsg : "正在执行操作...",
							waitTitle : "请稍后..."
						});
	}
	
	function onSave(){
		if (addForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("提示","数据校验错误！");
			return;
		}
		addForm.getForm().url = "add";
		addForm.getForm().submit(
						{
							success : function(form, action){
								if (action.result.msg) {
									Ext.Msg.alert("提示",action.result.msg);
								} else {
									Ext.Msg.alert("提示","保存成功");
									addWin.hide();
									gridStore.reload();
								}
							},
							failure : showFormSubmitFailureInfo,
							waitMsg : "正在执行保存操作...",
							waitTitle : "请稍后..."
						});
	}
	
	function onDelete(){
		var records = gridPanel.getSelectionModel().getSelections();
		if (records.length < 1) {
			Ext.MessageBox.alert('提示', '选择需要删除的数据');
		} else {
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
						ids += records[i].data.id ;
					}else{
						ids += ',' + records[i].data.id ;
					}
				}
				Ext.Ajax.request({params: {ids: ids},
					url: "delete",
					success: function(response) {
						var resp = Ext.util.JSON.decode(response.responseText);
						if (resp.success) {
							Ext.MessageBox.alert('提示','数据删除成功');
							editForm.getForm().reset() ;
						} else {
							Ext.MessageBox.alert('操作失败', resp.msg);
						}
						gridPanel.getStore().load();
					},
					failure:ajaxRequestFailure
				});
			}
		}
	
	
	
	
});

