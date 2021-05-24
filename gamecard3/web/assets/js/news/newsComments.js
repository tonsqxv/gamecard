Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		url:"list",
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
								name:"newsId",
								width:150,
								fieldLabel:"新闻ID"
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
			                              {header:"新闻ID",width:80,dataIndex:"newsId"},
			                              {header:"新闻标题",width:200,dataIndex:"newsTitle"},
			                              {header:"点评人",width:200,dataIndex:"memberName"},
			                              {header:"点评时间",width:200,renderer:datetimeRenderer,dataIndex:"commentTm"}
			                              ])
	});
	function datetimeRenderer(v){
		var value = v ;
		if(v != null && v != ''){
			value = formatDate(new Date(v.time)) ;
		}
		return value ;
	}
	
	var btnSearch = new Ext.Button({text:"查询",iconCls:'search',handler:onSearch }) ;
	var btnDelete = new Ext.Button({text:"删除",iconCls:'delete',handler:onDelete }) ;
	var btnDetail = new Ext.Button({text:"查看点评内容",iconCls:'detail',handler:onDetail }) ;
	
	
	new Ext.Viewport({
		layout:"border",
		items:[{
					region:"center",
					tbar:[btnSearch,"-",btnDelete,"-",btnDetail],
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
	
		gridPanel.getStore().baseParams = queryForm.getForm().getValues();
		gridPanel.getStore().baseParams["limit"] = pagingBar.pageSize;
		gridPanel.getStore().load();
	}
	
	 /*******************删除*******************************/
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
	/*******************查看点评内容****************************/
	function onDetail(){
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			detailWin.setTitle("点评内容");
			detailWin.show();
			var obj = {};
			for (p in record.data) {
				obj[p] = record.data[p]; 
			}
			detailForm.getForm().setValues(obj);
			
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时操作多条数据");
		}
	}
	// 编辑详情窗口
	var detailForm = new Ext.form.FormPanel({region:"center",height:500,autoScroll:true,frame:true,fileUpload:false,autoWidth:true,timeout:300,layout:"form"
		,items: [{
		        	 xtype:"htmleditor",
		        	 name:"context",
		        	 width:600,
		        	 height:400,
		        	 readOnly:true,
		        	 fieldLabel:"点评内容"
		         }]
	});
	var detailWin = new Ext.Window({height:450,constrainHeader:true,width:800,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
		,items: [
		         detailForm
		]
	});
	
});

