Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		url:"list",
		fields:[{name: "id"},
		        {name:"email"},
		        {name:"status"},
		        {name:"sendTm"},
		        {name:"isMember"}
		        ],
		totalProperty:"totalSize",
		root:"root"
	});
	
	var queryForm = new Ext.form.FormPanel({
		frame:true,
		region:"north",
		fileUpload:true ,
		autoHeight:true,
		items: [

			new Ext.form.FieldSet({
				labelWidth:80,
				autoHeight:true,
				layout:"column",
				title:"查询条件",
				items:[{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [{
										xtype:"textfield",
										width:150,
										name:"email",
										fieldLabel:"Email"
									}]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"combo",
									hiddenName:"status",
									emptyText : '全部',
									store:new Ext.data.SimpleStore({data: [['','全部'],['0','等待执行'],['1','已执行']], fields: ['key','value']}),
									valueField:"key",
									displayField:"value",
									width:150,
									triggerAction:"all",
									fieldLabel:"状态",
									editable:false,
									mode:"local"
							      	}]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"combo",
									hiddenName:"isMember",
									emptyText : '全部',
									store:new Ext.data.SimpleStore({data: [['','全部'],['0','否'],['1','是']], fields: ['key','value']}),
									valueField:"key",
									displayField:"value",
									width:150,
									triggerAction:"all",
									fieldLabel:"已成为会员",
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
			                              {header:"邮箱",width:200,dataIndex:"email"},
			                              {header:"状态",width:200,renderer:statusRenderer,dataIndex:"status"},
			                              {header:"发送时间",width:200,renderer:datetimeRenderer,dataIndex:"sendTm"},
			                              {header:"已成为会员",width:200,renderer:isMemberRenderer,dataIndex:"isMember"},
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
		if(v == 0){
			value = "等待执行" ;
		}else if(v == 1){
			value = "<font color = 'red'>已执行</font>" ;
		}
		return value ;
	}
	
	function isMemberRenderer(v){
		var value = "" ;
		if(v == 0){
			value = "否" ;
		}else if(v == 1){
			value = "<font color = 'red'>是</font>" ;
		}
		return value ;
	}
	
	var btnSearch = new Ext.Button({text:"查询",iconCls:'search',handler:onSearch }) ;
	var btnDelete = new Ext.Button({text:"删除",iconCls:'delete',handler:onDelete }) ;
	var btnInit = new Ext.Button({text:"初始化",iconCls:'reset',handler:onInit }) ;
	var btnImport = new Ext.Button({text:"导入",iconCls:'export',handler:onImport }) ;
	var btnSyncMemberData = new Ext.Button({text:"同步会员数据",iconCls:'next',handler:onSyncMemberData }) ;
	var btnClear = new Ext.Button({text:"清空表数据",iconCls:'stop',handler:onClear }) ;
	var btnExport = new Ext.Button({text:"导出",iconCls:'export',handler:onExport }) ;
	
	new Ext.Viewport({
		layout:"border",
		items:[{
			region:"center",
			tbar:[btnSearch,"-",btnDelete,"-",btnInit,"-",btnImport,"-",btnSyncMemberData,"-",btnClear,"-",btnExport],
			layout:"border",
			//bodyBorder:false,
			items:[
			       queryForm,
			       gridPanel
			       ]
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
	//初始化
	function onInit(){
		Ext.MessageBox.confirm('提示','确定要初始化？', function(result){
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
				Ext.Ajax.request({
					url: "init",
					success: function(response) {
						var resp = Ext.util.JSON.decode(response.responseText);
						if (resp.success) {
							Ext.MessageBox.alert('提示','初始化成功');
						} else {
							Ext.MessageBox.alert('操作失败', resp.msg);
						}
						gridPanel.getStore().load();
					},
					failure:ajaxRequestFailure
				});
			}
		});
		
	}
	
	//导入
	function onImport(){
		importWin.setTitle("Excel导入");
		importWin.show() ;
	}
	// 导入form
	var importForm = new Ext.form.FormPanel({
		region : "center",
		frame : true,
		fileUpload : true,
		method : "POST",
		items : [{
			layout : "column",
			items : [{
						columnWidth : 1,
						layout : 'form',
						labelWidth : 60,
						items : {
							xtype : 'textfield',
							inputType : "file",
							allowBlank:false,
							name : "uploadFile",
							fieldLabel : "选择文件"
						}
					}]
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
					text : "保存",
					iconCls:"save",
					handler : function() {
						if (importForm.getForm().isValid() == false) {
							Ext.MessageBox.alert("提示","数据校验错误！");
							return;
						}
						importForm.getForm().submit({
							url : "importExcel",
							fileUpload : true,
							success : function(form, action) {
								Ext.MessageBox.alert("提示","导入成功");
								importWin.hide();
								gridStore.reload();
							},
							failure : function(form, action) {
							},
							waitMsg : "导入中..."
						});
					}
				})],
		items :  [importForm]
	});
	/**************同步会员数据******************************/
	function onSyncMemberData(){
		Ext.Ajax.request({
			url: "sycnMemberData",
			success: function(response) {
				var resp = Ext.util.JSON.decode(response.responseText);
				if (resp.success) {
					Ext.MessageBox.alert('提示','数据同步成功,已同步<font color=red>'+resp.msg+'</font>条数据');
				} else {
					Ext.MessageBox.alert('操作失败', resp.msg);
				}
				gridPanel.getStore().load();
			},
			failure:ajaxRequestFailure
		});
	}
	/**************清空表数据************/
	function onClear(){
		Ext.Ajax.request({
			url: "clear",
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
	}
	/***************导出**************/
	function onExport(){
		queryForm.getForm().doAction('submit', {
			url : "export",
			success : function(form, action){},
			failure : showFormSubmitFailureInfo
		});
	//	window.location="export";
	}
});

