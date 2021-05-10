Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		url:"list",
		fields:[{name: "id"},
		        {name:"taskName"},
		        {name:"email"},
		        {name:"password"},
		        {name:"status"},
		        {name:"taskType"},
		        {name:"subject"},
		        {name:"emailTpl"},
		        {name:"readDataRows"},
		        {name:"runCycle"},
		        {name:"desc"},
		        {name:"startTm"},
		        {name:"stopTm"}
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
							columnWidth : .33,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [{
										xtype:"textfield",
										width:150,
										name:"taskName",
										fieldLabel:"任务名称"
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
			                              {header:"任务名称",width:100,dataIndex:"taskName"},
			                              {header:"邮箱",width:100,dataIndex:"email"},
			                              {header:"密码",width:100,dataIndex:"password"},
			                              {header:"状态",width:100,renderer:statusRenderer,dataIndex:"status"},
			                              {header:"任务类型",width:100,renderer:taskTypeRenderer,dataIndex:"taskType"},
			                              {header:"读取数据行数",width:80,dataIndex:"readDataRows"},
			                              {header:"运行间隔周期",width:80,dataIndex:"runCycle"},
			                              {header:"启动时间",width:150,renderer:datetimeRenderer,dataIndex:"startTm"},
			                              {header:"停止时间",width:150,renderer:datetimeRenderer,dataIndex:"stopTm"},
			                              {header:"描述",width:150,dataIndex:"desc"}
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
			value = "新建" ;
		}else if(v == 1){
			value = "<font color = 'red'>开启</font>" ;
		}else if(v == 2){
			value = "完成" ;
		}else if(v == 3){
			value = "手动停止" ;
		}else if(v == 4){
			value = "异常停止" ;
		}
		return value ;
	}
	function taskTypeRenderer(v){
		var value = "" ;
		if(v == 1){
			value = "目标客户" ;
		}else if(v == 2){
			value = "游客" ;
		}else if(v == 3){
			value = "会员" ;
		}
		return value ;
	}
	
	var btnSearch = new Ext.Button({text:"查询",iconCls:'search',handler:onSearch }) ;
	var btnAdd = new Ext.Button({text:"新增",iconCls:'add',handler:onAdd }) ;
	var btnEdit = new Ext.Button({text:"修改",iconCls:'edit',handler:onUpdate }) ;
	var btnDelete = new Ext.Button({text:"删除",iconCls:'delete',handler:onDelete }) ;
	var btnStart = new Ext.Button({text:"启动",iconCls:'next',handler:onStart }) ;
	var btnStop = new Ext.Button({text:"停止",iconCls:'stop',handler:onStop }) ;
	var btnTest = new Ext.Button({text:"邮件测试",iconCls:'export',handler:onTest }) ;
	
	
	new Ext.Viewport({
		layout:"border",
		items:[{
			region:"center",
			tbar:[btnSearch,"-",btnAdd,"-",btnEdit,"-",btnDelete,"-",btnStart,"-",btnStop,"-",btnTest],
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
	
	var editForm = new Ext.form.FormPanel({frame:true,fileUpload:false,autoWidth:true,timeout:300,layout:"column"
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
								name:"taskName",
								fieldLabel:"任务名称"
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
								name:"email",
								fieldLabel:"邮箱"
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
								name:"password",
								fieldLabel:"密码"
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
								name:"readDataRows",
								decimalPrecision:0,
								fieldLabel:"读取日志行数"
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
								name:"runCycle",
								decimalPrecision:0,
								fieldLabel:"运行间隔周期(<font color='red'>分钟</font>)"
							}]
				},{
					xtype : "panel",
					columnWidth : .5,
					labelWidth : 120,
					labelAlign : "left",
					layout : "form",
					items : [{
							xtype:"combo",
							hiddenName:"taskType",
							store:new Ext.data.SimpleStore({data: [['1','目标客户'],['2','游客'],['3','会员']], fields: ['key','value']}),
							valueField:"key",
							displayField:"value",
							width:150,
							allowBlank:false,
							triggerAction:"all",
							fieldLabel:"任务类型",
							editable:false,
							mode:"local"
					      	}]
				},{
					xtype : "panel",
					columnWidth : 1,
					labelWidth : 120,
					labelAlign : "left",
					layout : "form",
					items : [{
								xtype:"textarea",
								height:60,
								width:600,
								name:"desc",
								fieldLabel:"描述"
							}]
				},{
					xtype : "panel",
					columnWidth : 1,
					labelWidth : 120,
					labelAlign : "left",
					layout : "form",
					items : [{
								xtype:"textfield",
								width:600,
								allowBlank:false,
								name:"subject",
								fieldLabel:"主题"
							}]
				},{
					xtype : "panel",
					columnWidth : 1,
					labelWidth : 120,
					labelAlign : "left",
					layout : "form",
					items : [{
								xtype:"htmleditor",
								height:300,
								width:600,
								name:"emailTpl",
								fieldLabel:"邮件模板"
							}]
				}]
	});
	
	var btnSave = new Ext.Button({text:"保存",iconCls:'save',handler:onSaveOrUpdate}) ;
	// 编辑窗口
	var editWin = new Ext.Window({
		//height:200,
		autoHeight:true,
		constrainHeader:true,
		width:800,
		closeAction:"hide",
		resizable:false,
		isEditing:false,
		plain:true,
		modal:true
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
	//启动任务
	function onStart(){
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			Ext.MessageBox.confirm('提示','确定要启动该任务', function(result){
				if(result == 'yes'){
					Ext.Ajax.request({params: {id: record.data.id},
						url: "startTask",
						success: function(response) {
							var resp = Ext.util.JSON.decode(response.responseText);
							if (resp.success) {
								Ext.MessageBox.alert('提示','任务启动成功');
							} else {
								Ext.MessageBox.alert('操作失败', resp.msg);
							}
							gridPanel.getStore().load();
						},
						failure:ajaxRequestFailure
					});
				}
			});
			
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时操作多条数据");
		}
	}
	
	//停止任务
	function onStop(){
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			Ext.MessageBox.confirm('提示','确定要停止该任务', function(result){
				if(result == 'yes'){
					Ext.Ajax.request({params: {id: record.data.id},
						url: "stopTask",
						success: function(response) {
							var resp = Ext.util.JSON.decode(response.responseText);
							if (resp.success) {
								Ext.MessageBox.alert('提示','任务停止成功');
							} else {
								Ext.MessageBox.alert('操作失败', resp.msg);
							}
							gridPanel.getStore().load();
						},
						failure:ajaxRequestFailure
					});
				}
			});
			
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时操作多条数据");
		}
	}
	//邮件测试
	function onTest(){
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			Ext.Msg.prompt('测试邮箱账号', '输入收件人测试账号:', function(btn, text){
			    if (btn == 'ok'){
			    	Ext.Ajax.request({params: {'id': record.data.id,'email':text},
						url: "testEmail",
						success: function(response) {
							var resp = Ext.util.JSON.decode(response.responseText);
							if (resp.success) {
								Ext.MessageBox.alert('提示','已发送邮件请查收');
							} else {
								Ext.MessageBox.alert('操作失败', resp.msg);
							}
						},
						failure:ajaxRequestFailure
					});
			    }
			});
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时操作多条数据");
		}
	}
	
});

