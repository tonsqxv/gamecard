Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		url:"list",
		fields:[{name: "id"},
		        {name:"shippingOptionName"},
		        {name:"shippingOptionAmount"},
		        {name:"shippingOptionDesc"}
		        ],
		totalProperty:"totalSize",
		root:"root"
	});
	
	var queryForm_atrr_1 = new Ext.form.TextField({width:150,name:"shippingOptionName",fieldLabel:"物流名称"});
	
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
			                              {header:"物流名称",width:300,dataIndex:"shippingOptionName"},
			                              {header:"运费",width:300,dataIndex:"shippingOptionAmount"},
			                              {header:"描述",width:300,dataIndex:"shippingOptionDesc"}
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
	
	var editForm_atrr_1 = new Ext.form.TextField({width:150,name:"shippingOptionName",maxLength:100,allowBlank:false,fieldLabel:"物流名称",labelSeparator:'<font color=red>*</font>'});
	var editForm_atrr_2 = new Ext.form.NumberField({width:150,name:"shippingOptionAmount",decimalPrecision:2,allowBlank:false,fieldLabel:"运费",labelSeparator:'<font color=red>*</font>'});
	var editForm_atrr_3 = new Ext.form.TextArea({height:100,width:300,name:"shippingOptionDesc",allowBlank:true,maxLength:300,fieldLabel:"备注"});
	var editForm_atrr_4 = new Ext.form.Hidden({name:"id"});
	
	
	var editForm = new Ext.form.FormPanel({region:"center",height:200,frame:true,fileUpload:false,autoWidth:true,timeout:300
		,items: [
			new Ext.Panel({layout:"column",
				items: [
					new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_1]})
				]
			})
			,new Ext.Panel({layout:"column",
				items: [
					new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_2]})
				]
			})
			,new Ext.Panel({layout:"column",
				items: [
					new Ext.Panel({width:450,layout:"form",items: [editForm_atrr_3,editForm_atrr_4]})
				]
			})
		]
	});
	var btnSave = new Ext.Button({text:"保存",iconCls:'save',handler:onSaveOrUpdate}) ;
	// 编辑窗口
	var editWin = new Ext.Window({height:300,constrainHeader:true,width:450,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
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
	
	function showFormSubmitFailureInfo(form, action) {
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
	
	
	
});

