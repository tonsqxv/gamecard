Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		url:"list",
		fields:[{name: "id"},
		        {name:"discode"},
		        {name:"status"},
		        {name:"createTm"},
		        {name:"effectTm"},
		        {name:"useTm"}
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
								width:150,
								name:"discode",
								fieldLabel:"折扣码"
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
									store:new Ext.data.SimpleStore({data: [['','全部'],['0','新建'],['1','未使用'],['2','已使用'],['3','已过期']], fields: ['key','value']}),
									valueField:"key",
									displayField:"value",
									width:150,
									triggerAction:"all",
									fieldLabel:"任务状态",
									editable:false,
									mode:"local"
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
			                              {header:"折扣码",width:200,dataIndex:"discode"},
			                              {header:"状态",width:100,renderer:statusRenderer,dataIndex:"status"},
			                              {header:"创建时间",width:200,renderer:datetimeRenderer,dataIndex:"createTm"},
			                              {header:"有效日期",width:200,renderer:datetimeRenderer,dataIndex:"effectTm"},
			                              {header:"使用时间",width:200,renderer:datetimeRenderer,dataIndex:"useTm"},
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
			value = "未使用" ;
		}else if(v == 2){
			value = "<font color = 'red'>已使用</font>" ;
		}else if(v == 3){
			value = "<font color = 'red'>已过期</font>" ;
		}
		return value ;
	}
	var btnSearch = new Ext.Button({text:"查询",iconCls:'search',handler:onSearch }) ;
	var btnAdd = new Ext.Button({text:"批量生成",iconCls:'add',handler:onAdd }) ;
	var btnDelete = new Ext.Button({text:"删除",iconCls:'delete',handler:onDelete }) ;
	var btnEmailSend = new Ext.Button({text:"邮件发送折扣码",iconCls:'next',handler:onEmailSend }) ;
	
	new Ext.Viewport({
		layout:"border",
		items:[{
			region:"center",
			tbar:[btnSearch,"-",btnAdd,"-",btnDelete,"-",btnEmailSend],
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
	
	var editForm = new Ext.form.FormPanel({region:"center",height:300,frame:true,fileUpload:false,autoWidth:true,timeout:300
		,items: [{
					xtype:"panel",
					layout:"column",
					items: [{
							xtype : "panel",
							columnWidth : 1,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [{
								xtype:"numberfield",
								width:150,
								name:"count",
								minValue:1,
								decimalPrecision:0,
								allowBlank:false,
								fieldLabel:"数量"
							}]
						}]
			}]
	});
	var btnSave = new Ext.Button({text:"保存",iconCls:'save',handler:onSave}) ;
	// 编辑窗口
	var editWin = new Ext.Window({height:100,constrainHeader:true,width:350,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
		,tbar: [
		        btnSave
		]
		,items: [
		         editForm
		]
	});
	
	//新增
	function onAdd(){
		editWin.setTitle("新增");
		editWin.show();
		editForm.getForm().reset();
	}
	
	function onSave(){
		if (editForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("提示","数据校验错误！");
			return;
		}
		var waitMsg = "正在执行保存操作...";
		editForm.getForm().url = "create";
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
			Ext.Msg.alert("提示","保存成功");
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
					if(records[i].data.status != '0'){
						Ext.MessageBox.alert('提示','只有新建状态的数据可以删除');
						return ;
					}
					
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
	//邮件发送折扣码
	function onEmailSend(){
		Ext.Msg.prompt('邮箱发送折扣码', '输入收件人邮箱:', function(btn, text){
		    if (btn == 'ok'){
		    	Ext.Ajax.request({params: {'email':text},
					url: "emailSendDiscode",
					success: function(response) {
						var resp = Ext.util.JSON.decode(response.responseText);
						if (resp.success) {
							Ext.MessageBox.alert('提示','已发送邮件');
						} else {
							Ext.MessageBox.alert('操作失败', resp.msg);
						}
					},
					failure:ajaxRequestFailure
				});
		    }
		});
	}
	
});

