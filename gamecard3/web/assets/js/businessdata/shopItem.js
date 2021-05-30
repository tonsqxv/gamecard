Ext.onReady(function(){
	
	var gridStore = new Ext.data.JsonStore({
		url:"list",
		fields:[{name: "id"},
		        {name:"productId"},
		        {name:"cardMainImg"},
		        {name:"productName"},
		        {name:"memberId"},
		        {name:"amount"},
		        {name:"unitPrice"},
		        {name:"preSellPrice"},
		        {name:"itemTotal"},
		        {name:"sessionId"},
		        {name:"color"},
		        {name:"size"},
		        {name:"createTm"}
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
							items : [{xtype:"datefield",
  									width:150,
 									name:"createTmBegin",
 									format:"Y-m-d",
 									fieldLabel:"创建时间（开始）"
 									}]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{xtype:"datefield",
  									width:150,
 									name:"createTmEnd",
 									format:"Y-m-d",
 									fieldLabel:"创建时间（结束）"
 									}]
						},{
							xtype : "panel",
							columnWidth : .33,
							labelWidth : 150,
							labelAlign : "left",
							layout : "form",
							items : [{
									xtype:"combo",
									hiddenName:"isMember",
									emptyText : '全部',
									store:new Ext.data.SimpleStore({data: [['','全部'],['1','是'],['2','否']], fields: ['key','value']}),
									valueField:"key",
									displayField:"value",
									width:150,
									triggerAction:"all",
									fieldLabel:"是否是会员",
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
			                              {header:"id",width:50,dataIndex:"id"},
			                              {header:"productId",width:50,dataIndex:"productId"},
			                              {header:"产品名称",width:150,dataIndex:"productName"},
			                              {header:"销售单价",width:100,dataIndex:"unitPrice"},
			                              {header:"预销售单价",width:100,dataIndex:"preSellPrice"},
			                              {header:"折后产品单价",width:100,dataIndex:"unitPrice"},
			                              {header:"件数",width:80,dataIndex:"amount"},
			                              {header:"总金额",width:100,dataIndex:"itemTotal"},
			                              {header:"颜色",width:100,dataIndex:"color"},
			                              {header:"尺寸",width:100,dataIndex:"size"},
			                              {header:"创建时间",renderer:datetimeRenderer,width:150,dataIndex:"createTm"},
			                              {header:"sessionId",width:250,dataIndex:"sessionId"},
			                              {header:"会员",width:150,dataIndex:"memberId"}
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
	
	new Ext.Viewport({
		layout:"border",
		items:[{
			region:"center",
			tbar:[btnSearch,"-",btnDelete],
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
	
	
});

