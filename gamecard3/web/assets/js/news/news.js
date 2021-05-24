Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		url:"list",
		fields:[{name: "id"},
		        {name:"newsCategoryId"},
		        {name:"newsCategoryName"},
		        {name:"title"},
		        {name:"subTitle"},
		        {name:"context"},
		        {name:"status"},
		        {name:"createTm"}
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
				items:[{
							xtype : "panel",
							columnWidth : .5,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [{
								xtype:"textfield",
								name:"title",
								width:150,
								fieldLabel:"新闻标题"
							},{
								xtype:"hidden",
								name:"newsCategoryId"
							}]
						},{
							xtype : "panel",
							columnWidth : .5,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"combo",
									hiddenName:"status",
									emptyText : '全部',
									store:new Ext.data.SimpleStore({data: [['','全部'],['1','在线'],['2','下线']], fields: ['key','value']}),
									valueField:"key",
									displayField:"value",
									width:150,
									triggerAction:"all",
									fieldLabel:"状态",
									editable:false,
									mode:"local"
							      	}]
						}]
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
			                              {header:"ID",width:80,dataIndex:"id"},
			                              {header:"新闻分类",width:100,dataIndex:"newsCategoryName"},
			                              {header:"状态",width:100,renderer:statusRenderer,dataIndex:"status"},
			                              {header:"新闻标题",width:200,dataIndex:"title"},
			                              {header:"新闻栏目",width:200,dataIndex:"subTitle"},
			                              {header:"发布时间",width:200,renderer:datetimeRenderer,dataIndex:"createTm"}
			                              ])
	});
	function datetimeRenderer(v){
		var value = v ;
		if(v != null && v != ''){
			value = formatDate(new Date(v.time)) ;
		}
		return value ;
	}
	function statusRenderer(v){
		var value = "" ;
		if(v == 1){
			value = "在线" ;
		}else if(v == 2){
			value = "<font color='red'>下线</font>" ;
		}
		return value ;
	}
	
	var btnSearch = new Ext.Button({text:"查询",iconCls:'search',handler:onSearch }) ;
	var btnAdd = new Ext.Button({text:"新增",iconCls:'add',handler:onAdd }) ;
	var btnEdit = new Ext.Button({text:"修改",iconCls:'edit',handler:onUpdate }) ;
	var btnDelete = new Ext.Button({text:"删除",iconCls:'delete',handler:onDelete }) ;
	var btnStart = new Ext.Button({text:"启用",iconCls:'next',handler:onStart }) ;
	var btnClose = new Ext.Button({text:"关闭",iconCls:'close',handler:onClose }) ;
	var btnDetail = new Ext.Button({text:"查看点评内容",iconCls:'detail',handler:onDetail }) ;
	

	var treeLoader = new Ext.tree.TreeLoader({
		autoLoad:true,
		url : "../newsCategory/findNewsCategoryTree",
		//baseAttrs:{uiProvider :Ext.tree.TreeCheckNodeUI},
		listeners:{
		    beforeload:function(treeLoader, node){
		    // this.baseParams.pid = node.attributes.id;
		    }
		}
	});
	var rootNode = new Ext.tree.AsyncTreeNode({
		id : "0",
		text:"新闻分类",
	});
	var his_node ;
	var tabs = new Ext.TabPanel({
		region:"center",
	    activeTab: 0,
	    items: [{
			    	xtype:"panel",
			    	layout:"border",
			        title: '新闻分类',
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
					tbar:[btnSearch,"-",btnAdd,"-",btnEdit,"-",btnDelete,"-",btnStart,"-",btnClose,"-",btnDetail],
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
			queryForm.getForm().findField("newsCategoryId").setValue(his_node.attributes.id) ;
		}
		gridPanel.getStore().baseParams = queryForm.getForm().getValues();
		gridPanel.getStore().baseParams["limit"] = pagingBar.pageSize;
		gridPanel.getStore().load();
	}
	
	// 编辑详情窗口
	var editForm = new Ext.form.FormPanel({region:"center",height:600,autoScroll:true,frame:true,fileUpload:false,autoWidth:true,timeout:300
		,items: [
			new Ext.Panel({layout:"column",layout:"form"
				,items: [{
								xtype:"textfield",
								width:840,
								name:"title",
								allowBlank:false,
								fieldLabel:"新闻标题"
						}]
					})
			,new Ext.Panel({layout:"column",layout:"form"
				,items: [{
								xtype:"textfield",
								width:840,
								name:"subTitle",
								allowBlank:false,
								fieldLabel:"新闻栏目"
						}]
					})
			,new Ext.Panel({layout:"column"
				,items: [
					new Ext.Panel({id:"contextPanel",width:1000,layout:"form",items: []})
				]
			})
			,new Ext.Panel({layout:"column"
				,items: [{
								xtype:"hidden",
								name:"id"
						},{
							xtype:"hidden",
							name:"newsCategoryId"
						}]
					})
		]
	});
	
	var editWin = new Ext.Window({height:600,constrainHeader:true,width:1020,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
		,tbar: [
		        new Ext.Button({text:"保存",iconCls:'save',handler:onSaveOrUpdate })
		]
		,items: [
		         editForm
		]
	});

	//初始化编辑器
	var gridForm = new Ext.Panel({
	        frame: true,
	        labelAlign: 'top',
	        bodyStyle:'padding:5px',
	        width: 950,
	        trackResetOnLoad: true, // save value when data is loaded
	        layout: 'column',    // Specifies that the items will now be arranged in columns
	        items: [{
	                xtype: 'ckeditor',
	                id:'ckeditor-id',
	                fieldLabel: 'Editor',
	                name: 'context',
					CKConfig: {
						customConfig : '../ckeditor/config.js',
						height : 320,
						width: 950
							  }
	            	}]
	    });
	
	 var productDescPanel = Ext.getCmp("contextPanel") ;
	 productDescPanel.add(gridForm);
	 productDescPanel.doLayout();
	 /**************************************************/
	
	//操作类型变量
	var operator_type ;
	//新增
	function onAdd(){
		if(his_node == null || his_node.attributes.id == 0){
			Ext.MessageBox.alert("提示","请选择新闻分类");
			return;
		}
		operator_type = 'add';
		editWin.setTitle("新增");
		editWin.show();
		editForm.getForm().reset();
		editForm.getForm().findField("newsCategoryId").setValue((his_node==null)?'':his_node.attributes.id);
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
					},
					failure:ajaxRequestFailure
				});
			}
		}
	/***********启用************************/
	function onStart(){
		updateStatusByType(1) ;
	}
	/***********关闭************************/
	function onClose(){
		updateStatusByType(2) ;
	}
	function updateStatusByType(type){
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			Ext.Ajax.request({params: {'id':record.data.id,'type':type },
				url: "updateStatusByType",
				success: function(response) {
					var resp = Ext.util.JSON.decode(response.responseText);
					if (resp.success) {
						Ext.MessageBox.alert('提示','操作成功');
					} else {
						Ext.MessageBox.alert('操作失败', resp.msg);
					}
					gridPanel.getStore().load();
				},
				failure:ajaxRequestFailure
			});
			
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时修改多条数据");
		}
	}
	/**********查看点评内容********************/
	function onDetail(){
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			newsCommentsWin.setTitle("点评记录");
			newsCommentsWin.show() ;
			newsCommentsGridStore.baseParams["limit"] = newsCommentsPagingBar.pageSize;
			newsCommentsGridStore.baseParams["newsId"] = record.data.id;
			newsCommentsGridStore.load() ;
			
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时修改多条数据");
		}
	}
	var newsCommentsGridStore = new Ext.data.JsonStore({
		url:"../newsComments/list",
		fields:[{name: "id"},
		        {name:"newsId"},
		        {name:"memberId"},
		        {name:"context"},
		        {name:"newsTitle"},
		        {name:"memberName"},
		        {name:"commentTm"}
		        ],
		totalProperty:"totalSize",
		root:"root"
	});
	var newsCommentsPagingBar = new Ext.PagingToolbar({
		displayInfo:true,
		displayMsg:"当前显示 {0} - {1} 总记录数目 {2}",
		store:newsCommentsGridStore,
		pageSize:pageSize,
		emptyMsg:"未检索到数据"});
	
	var newsCommentsGridPanel = new Ext.grid.GridPanel({
		frame:true,
		tbar:newsCommentsPagingBar,
		loadMask:true,
		height:400,
		store : newsCommentsGridStore,
		//sm : checkboxSelectionMode3,
		enableHdMenu : false,
		cm : new Ext.grid.ColumnModel([ new Ext.grid.RowNumberer(),
		                                {header:"新闻ID",width:100,dataIndex:"newsId"},
			                            {header:"新闻标题",width:200,dataIndex:"newsTitle"},
			                            {header:"点评人",width:200,dataIndex:"memberName"},
			                            {header:"点评时间",width:200,renderer:datetimeRenderer,dataIndex:"commentTm"}
				]),
		listeners:{
			rowdblclick:showNewsCommentsDetailWin
		}
	});
	var newsCommentsWin = new Ext.Window({
		id:"uploadWinId",
		autoHeight:true,
		constrainHeader : true,
		width : 800,
		closeAction : "hide",
		resizable : false,
		isEditing : false,
		plain : true,
		modal : true,
		items :  [newsCommentsGridPanel]
	});
	//查看点评详情
	function showNewsCommentsDetailWin(){
		var records = newsCommentsGridPanel.getSelectionModel().getSelections();
		var record = newsCommentsGridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
				newsCommentsDetailWin.setTitle("点评内容") ;
				newsCommentsDetailWin.show() ;
				newsCommentsDetailForm.getForm().reset() ;
				var obj = {};
				for (p in record.data) {
					obj[p] = record.data[p]; 
				}
				newsCommentsDetailForm.getForm().setValues(obj);
		}
	}
	// 编辑详情窗口
	var newsCommentsDetailForm = new Ext.form.FormPanel({region:"center",height:300,autoScroll:true,frame:true,fileUpload:false,autoWidth:true,timeout:300,layout:"form"
		,items: [{
		        	 xtype:"htmleditor",
		        	 name:"context",
		        	 width:500,
		        	 height:300,
		        	 readOnly:true,
		        	 fieldLabel:"点评内容"
		         }]
	});
	var newsCommentsDetailWin = new Ext.Window({height:350,constrainHeader:true,width:650,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
		,items: [
		         newsCommentsDetailForm
		]
	});
});

