Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		url:"list",
		fields:[{name: "id"},
		        {name:"status"},
		        {name:"email"},
		        {name:"firstName"},
		        {name:"lastName"},
		        {name:"cityName"},
		        {name:"state"},
		        {name:"zipcode"},
		        {name:"countryName"},
		        {name:"country"},
		        {name:"createTm"},
		        {name:"taskStatus"},
		        {name:"sendTm"}
		        
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
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"textfield",
									width:150,
									name:"email",
									fieldLabel:"邮箱"
							}]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"combo",
									hiddenName:"status",
									emptyText : '全部',
									store:new Ext.data.SimpleStore({data: [['','全部'],['0','游客'],['1','会员']], fields: ['key','value']}),
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
							labelWidth : 150,
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
			                              {header:"邮箱",width:200,dataIndex:"email"},
			                              {header:"状态",width:150,renderer:statusRenderer,dataIndex:"status"},
			                              {header:"任务状态",width:200,renderer:taskStatusRenderer,dataIndex:"taskStatus"},
			                              {header:"发送时间",width:200,renderer:datetimeRenderer,dataIndex:"sendTm"},
			                              {header:"国家代码",width:100,dataIndex:"country"},
			                              {header:"国家",width:100,dataIndex:"countryName"},
			                              {header:"邮政编码",width:100,dataIndex:"zipcode"},
			                              {header:"省/市",width:100,dataIndex:"state"},
			                              {header:"城市",width:100,dataIndex:"cityName"},
			                              {header:"姓",width:80,dataIndex:"firstName"},
			                              {header:"名",width:80,dataIndex:"lastName"},
			                              {header:"创建时间",width:150,renderer:datetimeRenderer,dataIndex:"createTm"}
			                              ])
	});
	function statusRenderer(v){
		var value="" ;
		if(v == 0){
			value="游客" ;
		}else if(v == 1){
			value="<font color='red'>会员</font>" ;
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
	
	function datetimeRenderer(v){
		var value = v ;
		if(v != null && v != ''){
			value = formatDate(new Date(v.time)) ;
		}
		return value ;
	}
	
	var btnSearch = new Ext.Button({text:"查询",iconCls:'search',handler:onSearch }) ;
	var btnDelete = new Ext.Button({text:"删除",iconCls:'delete',handler:onDelete }) ;
	var btnSyncData = new Ext.Button({text:"同步订单数据",iconCls:'next',handler:onSyncData }) ;
	var btnSyncMemberData = new Ext.Button({text:"同步会员数据",iconCls:'next',handler:onSyncMemberData }) ;
	var btnInit = new Ext.Button({text:"初始化任务数据",iconCls:'reset',handler:onInit }) ;
	var btnResetOrderData = new Ext.Button({text:"重置订单数据",iconCls:'reset',handler:onResetOrderData }) ;
	
	new Ext.Viewport({
		layout:"border",
		items:[{
			region:"center",
			tbar:[btnSearch,"-",btnDelete,"-",btnSyncData,"-",btnSyncMemberData,"-",btnInit,"-",btnResetOrderData],
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
	/************同步数据************************/
	function onSyncData(){
		Ext.Ajax.request({
			url: "sycnData",
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
	/***********同步会员数据************************/
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
	//初始化
	function onInit(){
		Ext.MessageBox.confirm('提示','确定要初始化？', function(result){
			if (result == 'yes') {
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
	/************重置订单数据******************/
	function onResetOrderData(){
		Ext.MessageBox.confirm('提示','确定要初始化？', function(result){
			if (result == 'yes') {
				Ext.Ajax.request({
					url: "resetOrderData",
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
		});
	}
});

