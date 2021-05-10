Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		url:"list",
		fields:[{name: "id"},
		        {name:"fullName"},
		        {name:"email"},
		        {name:"company"},
		        {name:"phoneNumber"},
		        {name:"message"},
		        {name:"createTm"},
		        {name:"replyTm"},
		        {name:"status"},
		        {name:"replyMsg"}
		        ],
		totalProperty:"totalSize",
		root:"root"
	});
	
	var queryForm_atrr_1 = new Ext.form.TextField({width:150,name:"email",fieldLabel:"E-mail"});
	var queryForm_atrr_2 = new Ext.form.ComboBox({hiddenName:"status",emptyText : '全部',store:new Ext.data.SimpleStore({data: [['','全部'],['1','未回复'],['2','已回复']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,triggerAction:"all",fieldLabel:"状态",editable:false,mode:"local"});

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
							items : [queryForm_atrr_1]
						},
						{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [queryForm_atrr_2]
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
			                              {header:"姓名",dataIndex:"fullName"},
			                              {header:"E-mail",dataIndex:"email"},
			                              {header:"状态",width:150,renderer:statusRenderer,dataIndex:"status"},
			                              {header:"公司",dataIndex:"company"},
			                              {header:"联系方式",dataIndex:"phoneNumber"},
			                              {header:"留言时间",width:150,renderer:datetimeRenderer,dataIndex:"createTm"},
			                              {header:"回复时间",width:150,renderer:datetimeRenderer,dataIndex:"replyTm"}
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
		if(v == '1'){
			value="<font color='red'>未回复</font>" ;
		}else if( v == '2'){
			value="已回复(<font color='red'>已邮件回复</font>)" ;
		}
		return value ;
	}
	
	
	var btnSearch = new Ext.Button({text:"查询",iconCls:'search',handler:onSearch }) ;
	var btnReply = new Ext.Button({text:"回复",iconCls:'edit',handler:onReply }) ;
	var btnDelete = new Ext.Button({text:"删除",iconCls:'delete',handler:onDelete }) ;
	var btnDetail = new Ext.Button({text:"查看",iconCls:'detail',handler:onDetail }) ;
	
	new Ext.Viewport({
		layout:"border",
		items:[{
			region:"center",
			tbar:[btnSearch,"-",btnReply,"-",btnDelete,"-",btnDetail],
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
	/********************回复留言********************/
	var replyForm = new Ext.form.FormPanel({region:"center",height:200,frame:true,fileUpload:false,autoWidth:true,timeout:300
		,items: [{
					xtype:"panel",
					layout : "column",
					items:[{
					    	   xtype:"panel",
					    	   columnWidth : 1,
					    	   layout : "form",
					    	   items:[{
										xtype : 'textarea',
										name : "message",
										height:80,
										width:800,
										readOnly:true,
										fieldLabel : "客户留言"
									},{
										xtype : 'hidden',
										name:"id"
									}]
					       }]
				},{
					xtype:"panel",
					layout : "column",
					items:[{
					    	   xtype:"panel",
					    	   columnWidth : 1,
					    	   layout : "form",
					    	   items:[{
					    		   xtype:"textfield",
					    		   width:800,
					    		   readOnly:true,
					    		   name:"email",
					    		   allowBlank:false,
					    		   fieldLabel : "MailTo"
					    	   }]
					       }]
				},{
					xtype:"panel",
					layout : "column",
					items:[{
					    	   xtype:"panel",
					    	   columnWidth : 1,
					    	   layout : "form",
					    	   items:[{
					    		   xtype:"htmleditor",
					    		   height:350,
					    		   width:800,
					    		   name:"replyMsg",
					    		   allowBlank:false,
					    		   fieldLabel : "回复内容"
					    	   }]
					       }]
				}
		]
	});
	
	 
	var btnSave = new Ext.Button({text:"发送",iconCls:'save',handler:onReplySave}) ;
	// 编辑窗口
	var replyWin = new Ext.Window({height:550,constrainHeader:true,width:950,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
		,tbar: [
		        btnSave
		]
		,items: [
		         replyForm
		]
	});
	
	// 回复
	function onReply() {
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			replyWin.setTitle("回复");
			replyWin.show();
			replyForm.getForm().reset() ;
			var obj = {};
			for (p in record.data) {
				obj[p] = record.data[p]; 
			}
			
			replyForm.getForm().setValues(obj);
			btnSave.enable() ;
			
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时修改多条数据");
		}

	}
	function onReplySave(){
		if (replyForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("提示","数据校验错误！");
			return;
		}
		
		replyForm.getForm().submit(
						{
							url:"reply",
							waitMsg : "正在回复邮件...",
							waitTitle : "请稍后...",
							success : function(form, action){
								if (action.result.msg) {
									Ext.Msg.alert("提示",action.result.msg);
								} else {
									Ext.Msg.alert("提示","回复成功");
									replyWin.hide();
									gridStore.reload();
								}
							},
							failure : showFormSubmitFailureInfo,
							
						});
	}
	
	
	/*********************删除************************/
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
	/******************查看********************/
	function onDetail(){
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			replyWin.setTitle("详情");
			replyWin.show();
			replyForm.getForm().reset() ;
			var obj = {};
			for (p in record.data) {
				obj[p] = record.data[p]; 
			}
			
			replyForm.getForm().setValues(obj);
			btnSave.disable() ;
			
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时修改多条数据");
		}
	}
	
});

