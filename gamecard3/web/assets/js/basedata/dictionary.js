Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		url:"list",
		fields:[{name: "id"},
		        {name:"dicCode"},
		        {name:"dicValue"},
		        {name:"dicType"},
		        {name:"sort"},
		        {name:"desc"}
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
							columnWidth : .33,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [{
								xtype:"textfield",
								width:150,
								name:"dicCode",
								fieldLabel:"代码"
							}]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [{
								xtype:"textfield",
								width:150,
								name:"dicType",
								fieldLabel:"类型"
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
			                              {header:"代码",width:200,dataIndex:"dicCode"},
			                              {header:"值",width:200,dataIndex:"dicValue"},
			                              {header:"类型",width:200,dataIndex:"dicType"},
			                              {header:"排序",width:200,dataIndex:"sort"},
			                              ])
	});
	
	var btnSearch = new Ext.Button({text:"查询",iconCls:'search',handler:onSearch }) ;
	var btnAdd = new Ext.Button({text:"新增",iconCls:'add',handler:onAdd }) ;
	var btnEdit = new Ext.Button({text:"修改",iconCls:'edit',handler:onUpdate }) ;
	var btnDelete = new Ext.Button({text:"删除",iconCls:'delete',handler:onDelete }) ;
	
	new Ext.Viewport({
		layout:"border",
		items:[{
			region:"center",
			tbar:[btnSearch,"-",btnAdd,"-",btnEdit,"-",btnDelete],
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
	
	var editForm_atrr_1 = new Ext.form.TextField({width:150,name:"brand",maxLength:100,allowBlank:false,fieldLabel:"品牌名称",labelSeparator:'<font color=red>*</font>'});
	
	var editForm_atrr_2 = new Ext.form.Hidden({name:"id"});

	var editForm = new Ext.form.FormPanel({region:"center",autoHeight:true,frame:true,fileUpload:false,width:600,timeout:300,layout:"column"
		,items: [{
					xtype : "panel",
					columnWidth : .5,
					labelWidth : 120,
					labelAlign : "left",
					layout : "form",
					items : [{
								xtype:"textfield",
								width:150,
								allowBlank:false,
								name:"dicCode",
								fieldLabel:"代码"
							},{
								xtype:"hidden",
								name:"id"
							}]
				},{
					xtype : "panel",
					columnWidth : .5,
					labelWidth : 120,
					labelAlign : "left",
					layout : "form",
					items : [{
								xtype:"textfield",
								width:150,
								allowBlank:false,
								name:"dicValue",
								fieldLabel:"值"
							}]
				},{
					xtype : "panel",
					columnWidth : .5,
					labelWidth : 120,
					labelAlign : "left",
					layout : "form",
					items : [{
								xtype:"textfield",
								width:150,
								allowBlank:false,
								name:"dicType",
								fieldLabel:"类型"
							}]
				},{
					xtype : "panel",
					columnWidth : .5,
					labelWidth : 120,
					labelAlign : "left",
					layout : "form",
					items : [{
								xtype:"numberfield",
								width:150,
								allowBlank:false,
								name:"sort",
								decimalPrecision:0,
								fieldLabel:"排序"
							}]
				},{
					xtype : "panel",
					columnWidth : 1,
					labelWidth : 120,
					labelAlign : "left",
					layout : "form",
					items : [{
								xtype:"textarea",
								width:150,
								width:420,
								height:80,
								name:"desc",
								fieldLabel:"描述"
							}]
				}]
	});
	var btnSave = new Ext.Button({text:"保存",iconCls:'save',handler:onSaveOrUpdate}) ;
	// 编辑窗口
	var editWin = new Ext.Window({height:210,constrainHeader:true,width:600,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
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
		operator_type = 'add';
		editWin.setTitle("新增");
		editWin.show();
		editForm.getForm().reset();
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
	
	
});

