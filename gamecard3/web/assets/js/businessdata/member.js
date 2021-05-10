Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		url:"list",
		fields:[{name: "id"},
		        {name:"email"},
		        {name:"password"},
		        {name:"firstName"},
		        {name:"lastName"},
		        {name:"phoneNumber"},
		        {name:"locked"},
		        {name:"loginTm"},
		        {name:"loginCount"},
		        {name:"registerTm"},
		        {name:"taskStatus"},
		        {name:"sendTm"}
		        ],
		totalProperty:"totalSize",
		root:"root"
	});
	
	var queryForm_atrr_1 = new Ext.form.TextField({width:150,name:"email",fieldLabel:"邮箱"});
	
	var queryForm_atrr_2 = new Ext.form.ComboBox({hiddenName:"locked",emptyText : '全部',store:new Ext.data.SimpleStore({data: [['','全部'],['0','开启'],['1','锁定']], fields: ['key','value']}),valueField:"key",displayField:"value",width:150,name:"insureHirer.dutyType",triggerAction:"all",fieldLabel:"是否锁定",editable:false,mode:"local"});

	
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
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 120,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"combo",
									hiddenName:"taskStatus",
									emptyText : '全部',
									store:new Ext.data.SimpleStore({data: [['','全部'],['0','等待执行'],['1','已执行']], fields: ['key','value']}),
									valueField:"key",
									displayField:"value",
									width:150,
									triggerAction:"all",
									fieldLabel:"任务状态",
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
			                              {header:"邮箱",width:150,dataIndex:"email"},
			                              {header:"密码",width:100,dataIndex:"password"},
			                              {header:"任务状态",width:200,renderer:taskStatusRenderer,dataIndex:"taskStatus"},
			                              {header:"发送时间",width:200,renderer:datetimeRenderer,dataIndex:"sendTm"},
			                              {header:"姓",width:50,dataIndex:"firstName"},
			                              {header:"名",width:50,dataIndex:"lastName"},
			                              {header:"联系方式",width:120,dataIndex:"phoneNumber"},
			                              {header:"注册时间",width:150,renderer:datetimeRenderer,dataIndex:"registerTm"},
			                              {header:"状态",width:80,renderer:lockedRenderer,dataIndex:"locked"},
			                              {header:"上一次登陆时间",width:150,renderer:datetimeRenderer,dataIndex:"loginTm"},
			                              {header:"登陆次数",width:100,dataIndex:"loginCount"}
			                              ])
	});
	function datetimeRenderer(v){
		var value = v ;
		if(v != null && v != ''){
			value = formatDate(new Date(v.time)) ;
		}
		return value ;
	}
	function taskStatusRenderer(v){
		var value = "" ;
		if(v == 0){
			value = "等待执行" ;
		}else if(v == 1){
			value = "<font color = 'red'>已执行</font>" ;
		}
		return value ;
	}
	function lockedRenderer(v){
		var value ="" ;
		if(v == '0'){
			value = '开启' ;
		}else if(v == '1'){
			value = '<font color=\'red\'>锁定</font>' ;
		}
		return value ;
	}
	
	var btnSearch = new Ext.Button({text:"查询",iconCls:'search',handler:onSearch }) ;
	var btnAdd = new Ext.Button({text:"新增",iconCls:'add',handler:onAdd }) ;
	var btnEdit = new Ext.Button({text:"修改",iconCls:'edit',handler:onUpdate }) ;
	var btnDelete = new Ext.Button({text:"删除",iconCls:'delete',handler:onDelete }) ;
	var btnInit = new Ext.Button({text:"初始化任务数据",iconCls:'reset',handler:onInit }) ;
	
	new Ext.Viewport({
		layout:"border",
		items:[{
			region:"center",
			tbar:[btnSearch,"-",btnAdd,"-",btnEdit,"-",btnDelete,"-",btnInit],
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
	
	
	var editForm_atrr_1 = new Ext.form.TextField({width:150,name:"email",allowBlank:false,fieldLabel:"邮箱",validator:function(value){
		var reg = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;  
		   if(reg.test(value) === true){  
		      return  true;    
		      }  
		   return "邮箱格式错误，例如：example@macower.com" ;
		
	},labelSeparator:'<font color=red>*</font>'});
	
	var editForm_atrr_2 = new Ext.form.TextField({width:150,name:"password",allowBlank:false,fieldLabel:"密码",labelSeparator:'<font color=red>*</font>'});
	
	var editForm_atrr_3 = new Ext.form.TextField({width:150,name:"firstName",allowBlank:false,fieldLabel:"姓",labelSeparator:'<font color=red>*</font>'});
	
	var editForm_atrr_4 = new Ext.form.TextField({width:150,name:"lastName",allowBlank:false,fieldLabel:"名",labelSeparator:'<font color=red>*</font>'});
	
	var editForm_atrr_5 = new Ext.form.TextField({width:150,name:"phoneNumber",allowBlank:false,fieldLabel:"联系方式",labelSeparator:'<font color=red>*</font>'});
	
	var editForm_atrr_6 = new Ext.form.ComboBox({hiddenName:"locked",allowBlank:false,store:new Ext.data.SimpleStore({data: [['0','开启'],['1','锁定']], fields: ['key','value']}),valueField:"key",displayField:"value",value:'0',width:150,triggerAction:"all",fieldLabel:"是否锁定",editable:false,mode:"local",labelSeparator:'<font color=red>*</font>'});

	var editForm_atrr_7 = new Ext.form.Hidden({name:"id"});
	
	
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
					new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_3]})
				]
			})
			,new Ext.Panel({layout:"column",
				items: [
					new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_4]})
				]
			})
			,new Ext.Panel({layout:"column",
				items: [
					new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_5]})
				]
			})
			,new Ext.Panel({layout:"column",
				items: [
					new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_6,editForm_atrr_7]})
				]
			})
		]
	});
	var btnSave = new Ext.Button({text:"保存",iconCls:'save',handler:onSaveOrUpdate}) ;
	// 编辑窗口
	var editWin = new Ext.Window({height:300,constrainHeader:true,width:300,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
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
	
	
});

