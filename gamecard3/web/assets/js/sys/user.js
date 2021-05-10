Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		url:"list",
		fields:[{name: "id"},
		        {name:"userName"},
		        {name:"password"}
		        ],
		totalProperty:"totalSize",
		root:"root"
	});
	
	var queryForm_atrr_1 = new Ext.form.TextField({width:150,name:"userName",fieldLabel:"用户名"});
	
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
							items : [queryForm_atrr_1]
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
			                              {header:"用户名",width:300,dataIndex:"userName"},
			                              {header:"密码",width:300,dataIndex:"password"}
			                              ])
	});
	
	var btnSearch = new Ext.Button({text:"查询",iconCls:'search',handler:onSearch }) ;
	var btnAdd = new Ext.Button({text:"新增",iconCls:'add',handler:onAdd }) ;
	var btnEdit = new Ext.Button({text:"修改",iconCls:'edit',handler:onUpdate }) ;
	var btnDelete = new Ext.Button({text:"删除",iconCls:'delete',handler:onDelete }) ;
	var btnResetPassword = new Ext.Button({text:"重置密码",iconCls:'reset',handler:onResetPassword }) ;
	var btnRoleAssign = new Ext.Button({text:"分配角色",iconCls:'next',handler:onRoleAssign }) ;
	
	new Ext.Viewport({
		layout:"border",
		items:[{
			region:"center",
			tbar:[btnSearch,"-",btnAdd,"-",btnEdit,"-",btnDelete,"-",btnResetPassword,"-",btnRoleAssign],
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
	
	/****************重置密码*********************/
	
	function onResetPassword(){
		var records = gridPanel.getSelectionModel().getSelections();
		if (records.length < 1) {
			Ext.MessageBox.alert('提示', '选择需要操作的数据');
		} else {
			Ext.MessageBox.confirm('提示','确定要执行重置密码操作？', resetPassword);
		}
	}
	// 重置密码请求调用
	function resetPassword(result) {
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
					url: "resetPassword",
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
		}
	/*************************************/
	
	function onSearch(){
		
		if (queryForm.getForm().isValid() == false) {
			Ext.MessageBox.alert("提示","数据校验错误！");
			return ;
		}
		gridPanel.getStore().baseParams = queryForm.getForm().getValues();
		gridPanel.getStore().baseParams["limit"] = pagingBar.pageSize;
		gridPanel.getStore().load();
	}
	
	var editForm_atrr_1 = new Ext.form.TextField({width:150,name:"userName",maxLength:100,allowBlank:false,fieldLabel:"用户名",labelSeparator:'<font color=red>*</font>'});

	var editForm_atrr_2 = new Ext.form.TextField({width:150,name:"password",allowBlank:false,fieldLabel:"密码",labelSeparator:'<font color=red>*</font>'});
	
	var editForm_atrr_3 = new Ext.form.Hidden({name:"id"});
	
	
	var editForm = new Ext.form.FormPanel({region:"center",height:200,frame:true,fileUpload:false,autoWidth:true,timeout:300
		,items: [
			new Ext.Panel({layout:"column",
				items: [
					new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_1]})
				]
			})
			,new Ext.Panel({layout:"column",
				items: [
					new Ext.Panel({width:300,layout:"form",items: [editForm_atrr_2,editForm_atrr_3]})
				]
			})
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
	/**************分配角色**************************/
	var userId ;
	function onRoleAssign(){
		var records = gridPanel.getSelectionModel().getSelections();
		var record = gridPanel.getSelectionModel().getSelected();
		if (records.length == 1) {
			roleAssignWin.setTitle("分配角色");
			roleAssignWin.show();
			userId = record.data.id ;
			leftGridStore.load() ;
			rightGridStore.load() ;
		} else if (records.length < 1) {
			Ext.Msg.alert("提示","请选择一笔数据行");
		} else if (records.length > 1) {
			Ext.Msg.alert("提示","不能同时修改多条数据");
		}
	}
	
	//未角色列表
	var leftGridStore = new Ext.data.JsonStore({
		url:"../userRole/findNotAssignRoleByUserId",
		fields:[{name: "id"},
		        {name:"roleName"}
		        ],
		root:"root",
		listeners:{
			beforeload:function(store,option){
				store.baseParams["userId"] = userId ;
			}
		}
	});
	var leftCheckboxSelectionMode  = new Ext.grid.CheckboxSelectionModel({});
	var leftGridPanel = new Ext.grid.GridPanel(
			{
			region:"center",
			autoScroll:"true",
			frame:true,
			height:300,
			store:leftGridStore,
			sm: leftCheckboxSelectionMode,
			cm: new Ext.grid.ColumnModel([
			                              {header:"可选角色列表",width:200,dataIndex:"roleName"}
			                              ])
	});
	//已分配角色列表
	var rightGridStore = new Ext.data.JsonStore({
		url:"../userRole/findAssignRoleByUserId",
		fields:[{name: "id"},
		        {name:"roleName"}
		        ],
		root:"root",
		listeners:{
			beforeload:function(store,option){
				store.baseParams["userId"] = userId ;
			}
		}
	});
	var rightCheckboxSelectionMode  = new Ext.grid.CheckboxSelectionModel({});
	var rightGridPanel = new Ext.grid.GridPanel(
			{
			region:"center",
			autoScroll:"true",
			frame:true,
			height:300,
			store:rightGridStore,
			sm: rightCheckboxSelectionMode,
			cm: new Ext.grid.ColumnModel([
			                              {header:"已选角色列表",width:200,dataIndex:"roleName"}
			                              ])
	});
	var centerPanel = {
			layout:"form",
			columnWidth:1,
			frame:true,
			items: [{
						xtype:"panel",
						html:"",
						height:120,
						border:false
					},{
						xtype:"panel",
						height:25,
						border:false,
						frame:false,
						items:[{
								xtype: 'button',
								text: '>>',
								style:{textAlign:"center",paddingLeft: '30%'},
								width:50,
	        		    	    handler:leftToRight
								} ]
						
					},{
						xtype:"panel",
						html:"",
						height:30,
						border:false
					},{
						xtype:"panel",
						height:25,
						border:false,
						frame:false,
						items:[{
								xtype: 'button',
								text: '<<',
								style:{textAlign:"center",paddingLeft: '30%'},
								width:50,
	        		    	    handler:rightToLeft
								} ]
						
					},{
						xtype:"panel",
						html:"",
						height:90,
						border:false
					}]
		};
	
	function leftToRight(){
		if (leftCheckboxSelectionMode.hasSelection()) {
			var records = leftCheckboxSelectionMode.getSelections();
			var RecordType = rightGridStore.recordType ;

			for(var i = 0; i < records.length; i++){
				var m = new RecordType({
					id:records[i].data['id'],
					roleName: records[i].data['roleName']
				});
				rightGridStore.add(m);
			}
			Ext.each(records, function(item){
				this.remove(item);
			}, leftGridStore);
		}
	}
	function rightToLeft(){
		if (rightCheckboxSelectionMode.hasSelection()) {
			var records = rightCheckboxSelectionMode.getSelections();
			var RecordType = leftGridStore.recordType ;

			for(var i = 0; i < records.length; i++){
				var m = new RecordType({
					id:records[i].data['id'],
					roleName: records[i].data['roleName']
				});
				leftGridStore.add(m);
			}
			Ext.each(records, function(item){
				this.remove(item);
			}, rightGridStore);
		}
	}
	
	var roleAssignWin = new Ext.Window({height:360,constrainHeader:true,width:700,layout:"border",closeAction:"hide",resizable:false,isEditing:false,plain:true,modal:true
		,tbar: [
		        new Ext.Button({text:"保存",iconCls:'save',handler:saveAssignRole})
		]
		,items: [{
		        	xtype:"panel", 
		        	region:"center",
		        	layout:"column",
		        	items:[{
		        	    	   xtype:"panel",
		        	    	   columnWidth:.4,
		        	    	   layout:"column",
		        	    	   items:[leftGridPanel]
		        	       }, {
		        	    	   xtype:"panel",
		        	    	   columnWidth:.2,
		        	    	   layout:"column",
		        	    	   items:[centerPanel]
		        	       },{
		        	    	   xtype:"panel",
		        	    	   columnWidth:.4,
		        	    	   layout:"column",
		        	    	   items:[rightGridPanel]
		        	       }]
		         }]
	});
	function saveAssignRole(){
		var record = gridPanel.getSelectionModel().getSelected();
		var userId = record.data['id'] ;
		var records = rightGridPanel.getSelectionModel().getSelections();
		var roleIds = [] ;
		var params = {} ;
		Ext.each(rightGridStore.getRange(), function(record){
					this.push(record.data.id);
				},roleIds);
		if(roleIds.length > 0){
			params = {'userId':userId,'roleIds':roleIds}
		}else{
			Ext.Msg.alert("提示", "请选择角色");
			return ;
		}	
		
		Ext.Ajax.request({params: params,
			url: "assignRole",
			success: function(response) {
				var resp = Ext.util.JSON.decode(response.responseText);
				if (resp.success) {
					Ext.MessageBox.alert('提示','角色分配成功');
					roleAssignWin.hide() ;
				} else {
					Ext.MessageBox.alert('操作失败', resp.msg);
				}
			},
			failure:ajaxRequestFailure
		});
	}
	
});

