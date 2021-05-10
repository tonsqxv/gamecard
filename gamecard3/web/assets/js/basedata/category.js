Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		url:"list",
		fields:[{name: "id"},
		        {name:"name"},
		        {name:"sort"},
		        {name:"productDetailTpl"},
		        {name:"parentId"}
		        ],
		totalProperty:"totalSize",
		root:"root"
	});
	
	
	var queryForm = new Ext.form.FormPanel({
		frame:true,
		region:"north",
		autoHeight:true,
		items: [

			new Ext.form.FieldSet({
				labelWidth:80,
				autoHeight:true,
				layout:"column",
				title:"查询条件",
				items:[
						{
							xtype : "panel",
							columnWidth : .5,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [{
								xtype:"textfield",
								name:"name",
								width:150,
								fieldLabel:"名称"
							},{
								xtype:"hidden",
								name:"parentId"
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
	var checkboxSelectionMode  = new Ext.grid.CheckboxSelectionModel({});
	var gridPanel = new Ext.grid.GridPanel(
			{
			region:"center",
			tbar:pagingBar,
			store:gridStore,
			sm: checkboxSelectionMode,
			//stripeRows : true,
			cm: new Ext.grid.ColumnModel([
			                              checkboxSelectionMode,
			                              {header:"名称",width:200,dataIndex:"name"},
			                              {header:"排序",width:300,dataIndex:"sort"},
			                              {header:"产品详情模板",width:300,dataIndex:"productDetailTpl"}
			                              ])
	});
	
	var btnSearch = new Ext.Button({text:"查询",iconCls:'search',handler:onSearch }) ;
	var btnAdd = new Ext.Button({text:"新增",iconCls:'add',handler:onAdd }) ;
	var btnEdit = new Ext.Button({text:"修改",iconCls:'edit',handler:onUpdate }) ;
	var btnDelete = new Ext.Button({text:"删除",iconCls:'delete',handler:onDelete }) ;
	
	var treeLoader = new Ext.tree.TreeLoader({
		autoLoad:true,
		url : "findCategoryTree",
		//baseAttrs:{uiProvider :Ext.tree.TreeCheckNodeUI},
		listeners:{
		    beforeload:function(treeLoader, node){
		    // this.baseParams.pid = node.attributes.id;
		    }
		}
	});
	var rootNode = new Ext.tree.AsyncTreeNode({
		id : "0",
		text:"分类树",
	});
	var his_node ;
	var tabs = new Ext.TabPanel({
		region:"center",
	    activeTab: 0,
	    items: [{
			    	xtype:"panel",
			    	layout:"border",
			        title: '分类',
			        items:[{
			        	  	xtype:"treepanel",
			        	  	id:"categoryTree",
			        	  	region:"center",
							autoScroll : true,
							split : true,
							width : 250,
							//rootVisible: false,
							//checkModel:"cascade",
							//onlyLeafCheckable:false,
							loader: treeLoader,
					        root: rootNode,
					        listeners:{
							    click : function(node) {
							    	his_node = node ;
							    	onSearch() ;
							    }
							}
			        	  	
			        }]
			    }]
	});
	
	new Ext.Viewport({
		layout:"border",
		items:[{
					region:"west",
					width:"200",
					layout:"border",
					items:[tabs]
				},{
					region:"center",
					tbar:[btnSearch,"-",btnAdd,"-",btnEdit,"-",btnDelete],
					layout:"border",
					//bodyBorder:false,
					items:[queryForm, gridPanel ]
			}],
		listeners : {
			afterrender : function(view){
				onSearch() ;
			}
		}
	});
	
	function onSearch(){
		
		if (queryForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("提示","数据校验错误！");
			return ;
		}
		if(his_node != null){
			queryForm.getForm().findField("parentId").setValue(his_node.attributes.id) ;
		}
		gridPanel.getStore().baseParams = queryForm.getForm().getValues();
		gridPanel.getStore().baseParams["limit"] = pagingBar.pageSize;
		gridPanel.getStore().load();
	}
	
	
	var editForm = new Ext.form.FormPanel({region:"center",autoHeight:true,frame:true,fileUpload:false,width:300,timeout:300 
		,layout:"form"
		,items: [{
					xtype:"hidden",
					name:"id"
				},{
					xtype:"textfield",
					name:"parentId",
					width:150,
					readOnly:true,
					fieldLabel:"父模块"
				},{
					xtype:"textfield",
					name:"name",
					width:150,
					allowBlank:false,
					fieldLabel:"名称"
				},{
					xtype:"combo",
					hiddenName:"productDetailTpl",
					width:150,
					typeAhead: true,
					mode: 'remote',
					editable: false,
					displayField:"text",
					valueField:"key",
					triggerAction: "all",
					fieldLabel:"品牌",
					selectOnFocus:true,
					store:new Ext.data.JsonStore({
						autoLoad:true,
						url:'../dictionary/listBy',
						fields:[{name: 'key', mapping:'dicValue'},
						        {name: 'text', mapping:'dicValue'}],
						root:'root',
						listeners:{
							beforeload:function(s){
								s.baseParams["dicType"] = 1;
						  	}
				      }
					}),
					listeners:{
						'beforequery': function(qe){
							//每次都查询
							delete qe.combo.lastQuery;  
						}
					}
					
				},{
					xtype:"numberfield",
					name:"sort",
					width:150,
					allowBlank:false,
					fieldLabel:"排序"
				}
		]
	});
	
	var btnSave = new Ext.Button({text:"保存",iconCls:'save',handler:onSaveOrUpdate}) ;
	// 编辑窗口
	var editWin = new Ext.Window({height:200,constrainHeader:true,width:300,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
		,tbar: [
		        btnSave
		]
		,items: [
		         editForm
		]
	});
	//操作类型变量
	var operator_type ;
	//新增
	function onAdd(){
		/*if(his_node == null){
			Ext.MessageBox.alert("提示","请选择分类");
			return;
		}*/
		operator_type = 'add';
		editWin.setTitle("新增");
		editWin.show();
		editForm.getForm().reset();
		editForm.getForm().findField("parentId").setValue(his_node.attributes.id);
	}
	// 修改
	function onUpdate() {
		operator_type = 'update';
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			editWin.setTitle("修改");
			editWin.show();
			var obj = {};
			for (p in record.data) {
				obj[p] = record.data[p]; 
			}
			
			editForm.getForm().setValues(obj);
			
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时修改多条数据");
		}

	}
	function onSaveOrUpdate(){
		if (editForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("提示","数据校验错误！");
			return;
		}
		var waitMsg;
		if (operator_type == 'add') {
			waitMsg = "正在执行保存操作...";
			editForm.getForm().url = "add";
		} else if (operator_type == 'update') {
			waitMsg = "正在执行修改操作...";
			editForm.getForm().url = "update";
		}
		editForm.getForm().submit(
						{
							success : showSaveOrUpdateSuccessInfo,
							failure : showFormSubmitFailureInfo,
							waitMsg : waitMsg,
							waitTitle : "请稍后..."
						});
	}
	
	function showSaveOrUpdateSuccessInfo(form, action) {
		if (action.result.msg) {
			Ext.Msg.alert("提示",action.result.msg);
		} else {
			if (operator_type == 'add') {
				Ext.Msg.alert("提示","保存成功");
			} else if (operator_type == 'update') {
				Ext.Msg.alert("提示","更新成功");
			}
			editWin.hide();
			gridStore.reload();
			var tree = Ext.getCmp("categoryTree");
			tree.root.reload() ;
			tree.expandAll() ;
		}
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
						} else {
							Ext.MessageBox.alert('操作失败', resp.msg);
						}
						gridPanel.getStore().load();
						var tree = Ext.getCmp("categoryTree");
						tree.root.reload() ;
						tree.expandAll() ;
					},
					failure:ajaxRequestFailure
				});
			}
		}
	
	
	
});

