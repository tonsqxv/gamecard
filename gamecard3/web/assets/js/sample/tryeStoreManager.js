//<%@ page language="java" contentType="text/html; charset=UTF-8"%>
Ext
		.onReady(function() {
			Ext.QuickTips.init();
			Ext.form.Field.prototype.msgTarget = 'side';
			Ext.BLANK_IMAGE_URL = '../ext-2.0.2/resources/images/default/s.gif';

			var gridStore = new Ext.data.JsonStore({
				url : 'listTryeStores.action',
				fields : [ {
					name : 'id'
				}, {
					name : 'tryeBreed'
				}, {
					name : 'tryeModel'
				}, {
					name : 'tryeNo'
				}, {
					name : 'tryeInTm'
				}, {
					name : 'tryeKind'
				}, {
					name : 'usedMile'
				}, {
					name : 'checkEmp'
				}, {
					name : 'remark'
				}, {
					name : 'tryePrice'
				}, {
					name : 'tryeBuyCode'
				}, {
					name : 'tryeVacual'
				}, {
					name : 'valid'
				}, {
					name : 'minMiles'
				} ],
				totalProperty : 'totalSize',
				root : 'root'
			});

		
			var TryeChangedCombox = Ext
					.extend(
							Ext.form.TriggerField,
							{
								triggerClass : 'x-form-search-trigger',
								onTriggerClick : function() {
									var _curObj = this;
									var tryeDlg = Ext.getCmp("tryeDlg");
									if (Ext.isEmpty(tryeDlg, false)) {
										var CK = new Ext.grid.CheckboxSelectionModel(
												{
													singleSelect : true
												});
										
										var centerStore = new Ext.data.JsonStore(
												{
													autoLoad : true,
													url : 'listTryeDatas.action',
													fields : [ {name : 'tryeBreed'}, 
													           {name : 'tryeModel'},
													           {name : 'tryePrice'}, 
													           {name : 'tryeBuyCode'},
													           {name:'tryeVacual'},
														       {name:'valid'},
														       {name:'minMiles'}
													           ],
													root : 'root',
													totalProperty:'totalSize',
													listeners:{'beforeload':function(store){
											    		store.baseParams["limit"]=pagingBar.pageSize;
											    		store.baseParams["tryeData.valid"]='1';
												    }
												}
											});
										var pagingBar = new Ext.PagingToolbar({
											displayInfo:true,
											displayMsg:"${app:i18n_def('vmsoperation.page_prompt_current_data','当前显示')} {0} - {1} ${app:i18n_def('vmsoperation.page_prompt_total_data','总记录数目')} {2}",
											store:centerStore,
											pageSize:10,
											emptyMsg:"${app:i18n_def('vmsoperation.page_prompt_no_data','未检索到数据')}"
											});
										var queryForm2 = new Ext.form.FormPanel({
											frame:true,
											region:"north",
											autoHeight:true,
											items: [

												new Ext.form.FieldSet({
													labelWidth:80,
													height:40+23,
													layout:"column",
													title:"${app:i18n_def('vmsoperation.tryeData.page.1','查询条件')}",
													layout:'column',
													items:[
													       {	
													    	   xtype:"panel",
													    	   columnWidth:.5,
													    	   labelWidth:70,
													    	   labelAlign:"right",
													    	   layout:"form",
													    	   items:{
													    		   xtype:"combo",
													    		   id:"tryeBreed2",
													    		   hiddenName:"tryeData.tryeBreed",
													    		   fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.2','轮胎品牌')}',
													    		   typeAhead: true,
													    		   mode: 'remote',
													    		   width:120,
													    		   editable:false,
													    		   displayField:"text",
													    		   valueField:"key",
													    		   triggerAction: 'all',
													    		   emptyText:'${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}',
													    		   selectOnFocus:true,
													    		   store:new Ext.data.JsonStore({
													    			    url:'listTypeBreed.action',
													    				fields:[{name: 'key',mapping:'TEXT'},
													    				        {name:'text',mapping:'TEXT'}
													    				        ],
													    				root:'tryeBreedList',
													    				listeners:{
													    					load:function(t){
													    						  var all = new Ext.data.Record([]);
													    						  all.set('key', '');
													    		                  all.set('text', '${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}');
													    		                  t.insert(0, [all]);
													    					}
													    				}
													    		   }),
													    		   listeners:{
													    			   'beforequery': function(qe){
																			//每次都查询
																			delete qe.combo.lastQuery;  
																		},
													    			   select : function(){
																			Ext.getCmp("tryeModel2").setValue('');
																			Ext.getCmp("tryeModel2").store.removeAll();
																			Ext.getCmp("tryeModel2").store.load();
																			
																		}
													    	   }
													       }
													       },
													       {
													    	   xtype:"panel",
													    	   columnWidth:.5,
													    	   labelWidth:80,
													    	   labelAlign:"right",
													    	   layout:"form",
													    	   items:{
													    		 	xtype:"combo",
													    		   	id:"tryeModel2",
																	hiddenName:"tryeData.tryeModel",
																	fieldLabel:'${app:i18n_def('vmsoperation.tryeData.page.10','轮胎型号')}',
																	//labelWidth:100,
																	typeAhead: true,
																	mode: 'remote',
																	editable: false,
																	displayField:"text",
																	valueField:"key",
																	triggerAction: 'all',
																	emptyText:'${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}',
																	selectOnFocus:true,
																	width:120,
																	store:new Ext.data.JsonStore({
																		url:'listTryeModelForCombox.action',
																		fields:[{name: 'key', mapping:'tryeModel'},
																		        {name: 'text', mapping:'tryeModel'}],
																		root:'root',
																		listeners:{
																			beforeload:function(s){
																				s.baseParams["tryeData.tryeBreed"] = Ext.getCmp('tryeBreed2').getValue();
																		  	},
																		  	load:function(t){
													    						  var all = new Ext.data.Record([]);
													    						  all.set('key', '');
													    		                  all.set('text', '${app:i18n_def('vmsoperation.tryeData.page.3','请选择')}');
													    		                  t.insert(0, [all]);
													    					}
																      }
																	}),
																	listeners:{
																		'beforequery': function(qe){
																			//每次都查询
																			delete qe.combo.lastQuery;  
																		}
																	}
													    		 
													    	   		}
													       }
													       
													       
													       ]
													})
										]});
										
									function setTryeVacual(v, p, r) {
												var state = v;
												if(state == 1){
													v = "${app:i18n_def('vmsoperation.tryeStoreManager.page.30','是')}";
												}else if(v == 0){
													v='${app:i18n_def('vmsoperation.tryeStoreManager.page.31','否')}';
												}
												return v;
											}
										var centerPanel = new Ext.grid.GridPanel(
												{
													split : true,
													width : 230,
													// margins: '0 0 0 5',
													region : 'center',
													store : centerStore,
													tbar:pagingBar,
													sm : CK,
													cm : new Ext.grid.ColumnModel(
															[
																	new Ext.grid.RowNumberer(),
																	CK,
																	{
																		header : '${app:i18n_def('vmsoperation.tryeStoreManager.page.1','轮胎品牌')}',
																		dataIndex : "tryeBreed"
																	},
																	{
																		header : '${app:i18n_def('vmsoperation.tryeStoreManager.page.2','轮胎型号')}',
																		dataIndex : "tryeModel"
																	},
																	{
																		header : '${app:i18n_def('vmsoperation.tryeStoreManager.page.3','轮胎价格')}',
																		dataIndex : "tryePrice"
																	},
																	{
																		header : '${app:i18n_def('vmsoperation.tryeStoreManager.page.4','统购编码')}',
																		dataIndex : "tryeBuyCode"
																	},
																	{
																		header : '${app:i18n_def('vmsoperation.tryeData.page.12','是否真空胎')}',
																		dataIndex : "tryeVacual",
																		renderer:setTryeVacual
																	},
																	{
																		header : '${app:i18n_def('vmsoperation.tryeData.page.15','是否有效')}',
																		dataIndex : "valid",
																		renderer:setTryeVacual
																	},
																	{
																		header : '${app:i18n_def('vmsoperation.tryeData.page.19','最低标准里程(km)')}',
																		dataIndex : "minMiles"
																	} ])
												});
										
										
										tryeDlg = new Ext.Window(
												{
													width : 700,
													height : 450,
													frame : true,
													modal : true,
													title:'${app:i18n_def('vmsoperation.tryeStoreManager.page.selectTrye','选择轮胎基础数据')}',
													//closeAction : 'hide',
													layout : 'fit',
													tbar : [
															{
																text : '${app:i18n_def('vmsoperation.tryeStoreManager.page.6','查询')}',
																minWidth : 60,
																cls : 'x-btn-normal',
																handler : function() {
																	centerPanel.getStore().baseParams["tryeData.tryeBreed"] = queryForm2.getForm().findField("tryeData.tryeBreed").getValue() ;
																	centerPanel.getStore().baseParams["tryeData.tryeModel"] = queryForm2.getForm().findField("tryeData.tryeModel").getValue() ;
																	centerPanel.getStore().load();

																}
															},
															{
																text : '${app:i18n_def('vmsoperation.tryeStoreManager.page.7','选择')}',
																minWidth : 60,
																cls : 'x-btn-normal',
																handler : function() {
																	var record = centerPanel.getSelectionModel().getSelected();
																	_curObj.setValue(record.get("tryeBreed")+","+record.get("tryeModel"));
																	editForm.getForm().findField("tryeStore.tryeModel").setValue(record.get("tryeModel"));
																	editForm.getForm().findField("tryeStore.tryePrice").setValue(record.get("tryePrice"));
																	editForm.getForm().findField("tryeStore.tryeBreed").setValue(record.get("tryeBreed"));
																	editForm.getForm().findField("tryeStore.tryeBuyCode").setValue(record.get("tryeBuyCode"));

																	tryeDlg.close();
																}
															},
															{
																text : '${app:i18n_def('vmsoperation.tryeStoreManager.page.8','取消')}',
																minWidth : 60,
																cls : 'x-btn-normal',
																handler : function() {
																	tryeDlg.close();
																}
															} ], // end
																		// tbar
													items : [ {
														layout : 'border',
														items : [ queryForm2, centerPanel ]
													} ]
												})
									}
									;// end if
									tryeDlg.show(_curObj);
								} // end click
							});

			var dispalyDateFormat = new Ext.form.DateField({
				maxLength : 20,
				width : 120,
				disabled : false,
				format : "Y-m-d"
			});
			var his_node; //保存选择的网点节点
			var queryForm = new Ext.form.FormPanel({
				frame : true,
				region : "north",
				autoHeight : true,
				id : 'queryForm',
				items : [

				new Ext.form.FieldSet({
					labelWidth : 80,
					height : 100,
					layout : "column",
					title : "${app:i18n_def('vmsoperation.tryeStoreManager.page.9','查询条件')}",
					layout : 'column',
					items : [
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 100,
								labelAlign : "right",
								layout : "form",
								items : {
									xtype : "combo",
									id:"tryeBreed",
									hiddenName : "tryeStore.tryeBreed",
									fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.1','轮胎品牌')}',
									typeAhead : true,
									mode : 'remote',
									width : 125,
									editable: false,
									displayField : "text",
									valueField : "key",
									triggerAction : 'all',
									emptyText : '${app:i18n_def('vmsoperation.tryeStoreManager.page.10','请选择')}',
									selectOnFocus : true,
									listeners:{
										'beforequery': function(qe){
										//每次都查询
										delete qe.combo.lastQuery;  
										},
										select : function(){
											Ext.getCmp("tryeModel").setValue('');
											Ext.getCmp("tryeModel").store.removeAll();
											Ext.getCmp("tryeModel").store.load();
										}
									},
									store : new Ext.data.JsonStore({
										autoLoad : true,
										url : 'listTypeBreed.action',
										fields : [ {
											name : 'key',
											mapping : 'TEXT'
										}, {
											name : 'text',
											mapping : 'TEXT'
										} ],
										root : 'tryeBreedList',
										listeners:{
											load:function(t){
					    						  var all = new Ext.data.Record([]);
					    						  all.set('key', '');
					    		                  all.set('text', '${app:i18n_def('vmsoperation.tryeStoreManager.page.10','请选择')}');
					    		                  t.insert(0, [all]);
					    					}
										}
										
									})
								}
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 100,
								labelAlign : "right",
								layout : "form",
								items : {
					    		   	xtype:"combo",
					    		   	id:"tryeModel",
									hiddenName:"tryeStore.tryeModel",
									fieldLabel:'${app:i18n_def('vmsoperation.vehicleTyre.v2.2.page.7','轮胎型号')}',
									//labelWidth:100,
									typeAhead: true,
									mode: 'remote',
									editable: false,
									displayField:"text",
									valueField:"key",
									triggerAction: 'all',
									emptyText:'${app:i18n_def('vmsoperation.tryeStoreManager.page.10','请选择')}',
									selectOnFocus:true,
									width:125,
									store:new Ext.data.JsonStore({
										autoLoad:true,
										url:'listTryeModelForCombox.action',
										fields:[{name: 'key', mapping:'tryeModel'},
										        {name: 'text', mapping:'tryeModel'}],
										root:'root',
										listeners:{
											beforeload:function(s){
												s.baseParams["tryeData.tryeBreed"] = Ext.getCmp('tryeBreed').getValue();
										  	},
										  	load:function(t){
					    						  var all = new Ext.data.Record([]);
					    						  all.set('key', '');
					    		                  all.set('text', '${app:i18n_def('vmsoperation.tryeStoreManager.page.10','请选择')}');
					    		                  t.insert(0, [all]);
					    					}
								      }
									}),
									listeners:{
										'beforequery': function(qe){
											//每次都查询
											delete qe.combo.lastQuery;  
										}
									}
									
					    	   		}
							},
							
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 100,
								labelAlign : "right",
								layout : "form",
								items : {
									xtype : "textfield",
									name : "tryeStore.tryeNo",
									width : 125,
									maxLength:20,
									fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.11','轮胎编码')}'
								}
							},
							{
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 100,
								labelAlign : "right",
								layout : "form",
								items : {
									xtype : "combo",
									hiddenName : "tryeStore.tryeKind",
									fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.12','轮胎性质')}',
									typeAhead : true,
									mode : 'local',
									width : 125,
									editable : false,
									displayField : "text",
									valueField : "key",
									triggerAction : 'all',
									emptyText : '${app:i18n_def('vmsoperation.tryeStoreManager.page.10','请选择')}',
									selectOnFocus : true,
									store : new Ext.data.SimpleStore({
										fields : [ "key", "text" ],
										data : [ [ '', '${app:i18n_def('vmsoperation.tryeStoreManager.page.10','请选择')}' ], [ '1', '${app:i18n_def('vmsoperation.tryeStoreManager.page.13','新胎')}' ],
												[ '2', '${app:i18n_def('vmsoperation.tryeStoreManager.page.14','旧胎')}' ] ]
									})
								}
							}, {
								xtype : "panel",
								columnWidth : .33,
								labelWidth : 100,
								labelAlign : "right",
								layout : "form",
								items : [ {
									xtype : "datefield",
									format : 'Y-m-d',
									id : "tryeInTm",
									name : "tryeStore.tryeInTm",
									fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.15','入库时间')}',
									width : 125
								}, {
									xtype : 'hidden',
									name : 'tryeStore.deptId'
								} ]
							}

					]
				}) ]
			});

			function checkboxRenderer(v, p, r) {
				var trueValue = eval(this.id + '.trueValue');
				p.css += ' x-grid3-check-col-td';
				return '<div class="x-grid3-check-col'
						+ ((v == trueValue) ? '-on' : '') + ' x-grid3-cc-'
						+ this.id + '">&#160;</div>';
			}

			// 日期显示格式
			function datetimeRenderer(v, p, r) {
				if ((v == null) || (v.length < 19))
					return v;
				var dateFormat = eval(this.id + '.format');
				if (typeof (v) == "string") {
					v = v.replace('T', ' ');
					v = Date.parseDate(v, "Y-m-d H:i:s");
				}
				return v.format(dateFormat);
			}

			var tryeKind = new Ext.form.ComboBox({
				typeAhead : true,
				lazyRender : true,
				width : 150,
				mode : "local",
				valueField : "key",
				displayField : "text",
				triggerAction : "all",
				store : new Ext.data.SimpleStore({
					data : [ [ '', '${app:i18n_def('vmsoperation.tryeStoreManager.page.10','请选择')}' ], [ '1', '${app:i18n_def('vmsoperation.tryeStoreManager.page.13','新胎')}' ], [ '2', '${app:i18n_def('vmsoperation.tryeStoreManager.page.14','旧胎')}' ] ],
					fields : [ 'key', 'text' ]
				})
			});
			
			// 下拉框处理函数
			function comboboxRenderer(v, p, r) {
				if (v != null) {
					var records = eval(this.id
							+ '.initialConfig.store.getRange()');
					var keyField = eval(this.id + '.valueField');
					var displayField = eval(this.id + '.displayField');
					for ( var i = 0; i < records.length; i++) {
						var record = records[i];
						if (record.get(keyField) == v) {
							return record.get(displayField);
						}
					}
				}
				return v;
			}

			var pagingBar = new Ext.PagingToolbar(
					{
						displayInfo : true,
						displayMsg : "${app:i18n_def('vmsoperation.tryeStoreManager.page.16','当前显示 {0} - {1} 总记录数目 {2}')}",
						store : gridStore,
						pageSize : 20,
						emptyMsg : "${app:i18n_def('vmsoperation.tryeStoreManager.page.17','未检索到数据')}"
					});
			var checkboxSelectionMode = new Ext.grid.CheckboxSelectionModel({});
			var gridPanel = new Ext.grid.GridPanel({
				region : 'center',
				tbar : pagingBar,
				store : gridStore,
				sm : checkboxSelectionMode,
				// stripeRows : true,
				cm : new Ext.grid.ColumnModel([ new Ext.grid.RowNumberer(),
						checkboxSelectionMode, {
							header : '${app:i18n_def('vmsoperation.tryeStoreManager.page.1','轮胎品牌')}',
							dataIndex : "tryeBreed",
							sortable : false
						}, {
							header : '${app:i18n_def('vmsoperation.tryeStoreManager.page.2','轮胎型号')}',
							dataIndex : "tryeModel",
							sortable : false
						}, {
							header : '${app:i18n_def('vmsoperation.tryeStoreManager.page.11','轮胎编码')}',
							dataIndex : "tryeNo",
							sortable : false
						}, {
							header : '${app:i18n_def('vmsoperation.tryeStoreManager.page.15','入库时间')}',
							dataIndex : "tryeInTm",
							sortable : false,
							renderer : datetimeRenderer,
							id : "dispalyDateFormat"
						}, {
							header : '${app:i18n_def('vmsoperation.tryeStoreManager.page.12','轮胎性质')}',
							dataIndex : "tryeKind",
							sortable : false,
							renderer : comboboxRenderer,
							id : "tryeKind"
						}, {
							header : '${app:i18n_def('vmsoperation.tryeStoreManager.page.18','已用里程(km)')}',
							dataIndex : "usedMile",
							sortable : false
						}, {
							header : '${app:i18n_def('vmsoperation.tryeStoreManager.page.19','验收人')}',
							dataIndex : "checkEmp",
							sortable : false
						}, {
							hidden : true,
							header : '${app:i18n_def('vmsoperation.tryeStoreManager.page.20','备注')}',
							dataIndex : "remark",
							sortable : false
						}, {
							hidden : true,
							header : '${app:i18n_def('vmsoperation.tryeStoreManager.page.3','轮胎价格')}',
							dataIndex : "tryePrice",
							sortable : false
						},

						{
							hidden : true,
							header : '${app:i18n_def('vmsoperation.tryeStoreManager.page.4','统购编码')}',
							dataIndex : "tryeBuyCode",
							sortable : false
						}, {
							hidden : true,
							header : '${app:i18n_def('vmsoperation.tryeStoreManager.page.21','是否真空胎')}',
							dataIndex : "tryeVacual",
							sortable : false
						}, {
							hidden : true,
							header : '${app:i18n_def('vmsoperation.tryeStoreManager.page.22','是否有效')}',
							dataIndex : "valid",
							sortable : false
						}, {
							hidden : true,
							header : '${app:i18n_def('vmsoperation.tryeStoreManager.page.23','最低标准里程')}',
							dataIndex : "minMiles",
							sortable : false
						} ])
			});

			var btnSearch = new Ext.Button({
				text : "${app:i18n_def('vmsoperation.tryeStoreManager.page.6','查询')}",
				cls : 'x-btn-normal',
				minWidth : 60,
				handler : onSearch
			});

			var btnDetail = new Ext.Button({
				text : "${app:i18n_def('vmsoperation.tryeStoreManager.page.24','查看详细')}",
				cls : 'x-btn-normal',
				minWidth : 60,
				handler : onDetail
			});
			var btnAdd = new Ext.Button({
				text : "${app:i18n_def('vmsoperation.tryeStoreManager.page.25','入库')}",
				cls : 'x-btn-normal',
				minWidth : 60,
				handler : onAdd
			});
			var btnUpdate = new Ext.Button({
				text : "${app:i18n_def('people.edit.title','修改')}",
				cls : 'x-btn-normal',
				minWidth : 60,
				handler : onUpdate
			});
			var btnExport = new Ext.Button({
				text : "${app:i18n_def('vmsoperation.tryeStoreManager.page.26','导出')}",
				cls : 'x-btn-normal',
				minWidth : 60,
				handler : onExport
			});
			var btnSave = new Ext.Button({
				text : "${app:i18n_def('vmsoperation.tryeStoreManager.page.27','保存')}",
				handler : onSaveOrUpdate
			});

			var btnCancel = new Ext.Button({
				text : "${app:i18n_def('vmsoperation.tryeStoreManager.page.8','取消')}",
				handler : onCancel
			});

			var editForm = new Ext.form.FormPanel({
				width : 500,
				height : 400,
				// autoHeight:true,
				modal : true,
				border : false,
				bodyBorder : false,
				closable : false,
				resizable : false,
				layout : 'fit',
				id : 'aa',
				closeAction : 'hide',
				items : {
					xtype : 'panel',
					height : 23 * 17 + 17,
					layout : 'column',
					frame : true,
					id : 'bb',
					items : [ {
						xtype : 'panel',
						layout : 'form',
						labelWidth : 120,
						labelAlign : 'right',
						id : 'c',
						columnWidth : .5,
						items : new TryeChangedCombox({
							readOnly:true,
							name : "tryeData",
							allowBlank : false,
							fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.selectTrye.baseData','轮胎基础数据')}',
							width : 150,
							labelSeparator : '<font color=red>*</font>'
						})
					}, 
					{
						xtype : 'panel',
						layout : 'form',
						labelWidth : 120,
						labelAlign : 'right',
						columnWidth : .5,
						items : {
							xtype : 'textfield',
							allowBlank : false,
							readOnly:true,
							name : 'tryeStore.tryeBreed',
							fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.1','轮胎品牌')}',
							width : 150,
							labelSeparator : '<font color=red>*</font>'
						}
					},{
						xtype : 'panel',
						layout : 'form',
						labelWidth : 120,
						labelAlign : 'right',
						columnWidth : .5,
						items : {
							xtype : 'textfield',
							allowBlank : false,
							readOnly:true,
							name : 'tryeStore.tryeModel',
							fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.2','轮胎型号')}',
							width : 150,
							labelSeparator : '<font color=red>*</font>'
						}
					},
					{
						xtype : 'panel',
						layout : 'form',
						labelWidth : 120,
						labelAlign : 'right',
						columnWidth : .5,
						items : {
							xtype : 'textfield',
							allowBlank : false,
							readOnly:true,
							name : 'tryeStore.tryeBuyCode',
							fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.4','统购编码')}',
							width : 150,
							labelSeparator : '<font color=red>*</font>'
						}
					},
					{
						xtype : 'panel',
						layout : 'form',
						labelWidth : 120,
						labelAlign : 'right',
						columnWidth : .5,
						items : {
							xtype : 'numberfield',
							allowBlank : false,
							allowNegative : false,
							name : 'tryeStore.tryePrice',
							fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.28','轮胎价格(元)')}',
							id : "tryeStore.tryePrice",
							maxLength : 9,
							width : 150,
							value : 0,
							minValue:0,
							maxValue:4000,
							labelSeparator : '<font color=red>*</font>'
						}
					}, {
						xtype : 'panel',
						layout : 'form',
						labelWidth : 120,
						labelAlign : 'right',
						columnWidth : .5,
						items : {
							xtype : 'textfield',
							allowBlank : false,
							name : 'tryeStore.tryeNo',
							fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.11','轮胎编码')}',
							width : 150,
							maxLength : 11,
							minLength : 10,
							labelSeparator : '<font color=red>*</font>'
						}
					}, {
						xtype : 'panel',
						layout : 'form',
						labelWidth : 120,
						labelAlign : 'right',
						columnWidth : .5,
						items : {
							xtype : 'datefield',
							format : 'Y-m-d',
							allowBlank : false,
							name : 'tryeStore.tryeInTm',
							fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.15','入库时间')}',
							value : new Date(),
							width : 150,
							labelSeparator : '<font color=red>*</font>'
						}
					}, {
						xtype : 'panel',
						layout : 'form',
						labelWidth : 120,
						labelAlign : 'right',
						columnWidth : .5,
						items : {
							xtype : 'textfield',
							allowBlank : false,
							allowNegative : false,
							name : 'tryeStore.checkEmp',
							fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.19','验收人')}',
							width : 150,
							maxLength:15,
							labelSeparator : '<font color=red>*</font>'
						}
					}, {
						xtype : 'panel',
						layout : 'form',
						labelWidth : 120,
						labelAlign : 'right',
						columnWidth : .5,
						items : {
							xtype : "numberfield",
							name : "tryeStore.usedMile",
							fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.29','已使用里程')}',
							width : 150,
							labelSeparator : '<font color=red>*</font>',
							maxLength:9,
							value : 0,
							decimalPrecision:0,
							allowBlank : false,
							allowNegative : false
						}
					}, {
						xtype : 'panel',
						layout : 'form',
						labelWidth : 120,
						labelAlign : 'right',
						columnWidth : 1,
						items : [ {
							xtype : "textarea",
							name : "tryeStore.remark",
							fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.20','备注')}',
							maxLength:150,
							width : 460,
							height : 92
						}, {
							xtype : 'hidden',
							name : 'tryeStore.id'
						}, {
							xtype : 'hidden',
							name : 'tryeStore.deptId'
						} ]
					} ]
				}
			});
			var editWin = new Ext.Window({
				frame : true,
				width : 650,
				height : 410,
				layout : "fit",
				modal : true,
				closeAction : "hide",
				buttonAlign : 'right',
				tbar : [ btnSave, btnCancel ],
				items : [ editForm ]
			});

			var detailForm = new Ext.form.FormPanel(
					{
						width : 500,
						height : 400,
						// autoHeight:true,
						modal : true,
						border : false,
						bodyBorder : false,
						closable : false,
						resizable : false,
						layout : 'fit',
						closeAction : 'hide',
						items : {
							xtype : 'panel',
							height : 23 * 17 + 17,
							layout : 'column',
							frame : true,
							items : [
									{
										xtype : 'panel',
										layout : 'form',
										labelWidth : 120,
										labelAlign : 'right',
										columnWidth : .5,
										items : {
											xtype : 'textfield',
											readOnly:true,
											name : 'tryeStore.tryeBreed',
											fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.1','轮胎品牌')}',
											width : 150
										}
									},
									{
										xtype : 'panel',
										layout : 'form',
										labelWidth : 120,
										labelAlign : 'right',
										columnWidth : .5,
										items : {
											xtype : 'textfield',
											readOnly:true,
											name : 'tryeStore.tryeModel',
											fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.2','轮胎型号')}',
											width : 150
										}
									},
									{
										xtype : 'panel',
										layout : 'form',
										labelWidth : 120,
										labelAlign : 'right',
										columnWidth : .5,
										items : {
											xtype : 'textfield',
											readOnly:true,
											name : 'tryeStore.tryeBuyCode',
											fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.4','统购编码')}',
											width : 150
										}
									},
									{
										xtype : 'panel',
										layout : 'form',
										labelWidth : 120,
										labelAlign : 'right',
										columnWidth : .5,
										items : {
											xtype : 'numberfield',
											allowNegative : false,
											readOnly:true,
											name : 'tryeStore.tryePrice',
											fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.3','轮胎价格')}',
											width : 150
										}
									},
									{
										xtype : 'panel',
										layout : 'form',
										labelWidth : 120,
										labelAlign : 'right',
										columnWidth : .5,
										items : {
											xtype : "combo",
											hiddenName : "tryeStore.tryeVacual",
											fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.21','是否真空胎')}',
											typeAhead : true,
											readOnly:true,
											mode : 'local',
											disabled:true,
											width : 150,
											displayField : "text",
											valueField : "key",
											triggerAction : 'all',
											emptyText : '${app:i18n_def('vmsoperation.tryeStoreManager.page.10','请选择')}',
											selectOnFocus : true,
											store : new Ext.data.SimpleStore({
												fields : [ "key", "text" ],
												data : [ [ '', '${app:i18n_def('vmsoperation.tryeStoreManager.page.10','请选择')}' ],
														[ '1', '${app:i18n_def('vmsoperation.tryeStoreManager.page.30','是')}' ],
														[ '0', '${app:i18n_def('vmsoperation.tryeStoreManager.page.31','否')}' ] ]
											})
										}
									},
									{
										xtype : 'panel',
										layout : 'form',
										labelWidth : 120,
										labelAlign : 'right',
										columnWidth : .5,
										items : {
											xtype : "combo",
											hiddenName : "tryeStore.valid",
											fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.22','是否有效')}',
											typeAhead : true,
											readOnly:true,
											mode : 'local',
											width : 150,
											disabled:true,
											displayField : "text",
											valueField : "key",
											triggerAction : 'all',
											emptyText : '${app:i18n_def('vmsoperation.tryeStoreManager.page.10','请选择')}',
											selectOnFocus : true,
											store : new Ext.data.SimpleStore({
												fields : [ "key", "text" ],
												data : [ [ '', '${app:i18n_def('vmsoperation.tryeStoreManager.page.10','请选择')}' ],
														[ '1', '${app:i18n_def('vmsoperation.tryeStoreManager.page.30','是')}' ],
														[ '0', '${app:i18n_def('vmsoperation.tryeStoreManager.page.31','否')}' ] ]
											})
										}
									},
									{
										xtype : 'panel',
										layout : 'form',
										labelWidth : 120,
										labelAlign : 'right',
										columnWidth : .5,
										items : {
											xtype : 'numberfield',
											name : 'tryeStore.minMiles',
											readOnly:true,
											width : 150,
											allowNegative : false,
											fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.23','最低标准里程')}'
										}
									},
									{
										xtype : 'panel',
										layout : 'form',
										labelWidth : 120,
										labelAlign : 'right',
										columnWidth : .5,
										items : {
											xtype : 'textfield',
											allowNegative : false,
											readOnly:true,
											name : 'tryeStore.checkEmp',
											fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.19','验收人')}',
											width : 150
										}
									},
									{
										xtype : 'panel',
										layout : 'form',
										labelWidth : 120,
										labelAlign : 'right',
										columnWidth : .5,
										items : {
											xtype : 'textfield',
											readOnly:true,
											name : 'tryeStore.tryeNo',
											fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.11','轮胎编码')}',
											width : 150
										}
									},
									{
										xtype : 'panel',
										layout : 'form',
										labelWidth : 120,
										labelAlign : 'right',
										columnWidth : .5,
										items : {
											xtype : "numberfield",
											name : "tryeStore.usedMile",
											fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.29','已使用里程')}',
											width : 150,
											readOnly : true,
											allowNegative : false
										}
									},
									{
										xtype : 'panel',
										layout : 'form',
										labelWidth : 120,
										labelAlign : 'right',
										columnWidth : .5,
										items : {
											xtype : "combo",
											hiddenName : "tryeStore.tryeKind",
											fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.12','轮胎性质')}',
											typeAhead : true,
											readOnly:true,
											mode : 'local',
											width : 150,
											disabled:true,
											displayField : "text",
											valueField : "key",
											triggerAction : 'all',
											emptyText : '${app:i18n_def('vmsoperation.tryeStoreManager.page.10','请选择')}',
											selectOnFocus : true,
											store : new Ext.data.SimpleStore({
												fields : [ "key", "text" ],
												data : [ [ '', '${app:i18n_def('vmsoperation.tryeStoreManager.page.10','请选择')}' ], [ '1', '${app:i18n_def('vmsoperation.tryeStoreManager.page.13','新胎')}' ],
															[ '2', '${app:i18n_def('vmsoperation.tryeStoreManager.page.14','旧胎')}' ] ]
											})
										}
									},
									{
										xtype : 'panel',
										layout : 'form',
										labelWidth : 120,
										labelAlign : 'right',
										columnWidth : .5,
										items : {
											xtype : 'datefield',
											format : 'Y-m-d',
											readOnly:true,
											name : 'tryeStore.tryeInTm',
											fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.15','入库时间')}',
											width : 150
										}
									}, {
										xtype : 'panel',
										layout : 'form',
										labelWidth : 120,
										labelAlign : 'right',
										columnWidth : 1,
										items : {
											xtype : "textarea",
											name : "tryeStore.remark",
											readOnly:true,
											fieldLabel : '${app:i18n_def('vmsoperation.tryeStoreManager.page.20','备注')}',
											width : 460,
											height : 92
										}
									} ]
						}
					});

			var detailWin = new Ext.Window({
				frame : true,
				width : 650,
				height : 410,
				layout : "fit",
				modal : true,
				closeAction : "hide",
				buttonAlign : 'right',
				tbar : [ {
					xtype : "button",
					text : "${app:i18n_def('vmsoperation.tryeStoreManager.page.27','保存')}",
					disabled : true
				}, {
					xtype : "button",
					text : "${app:i18n_def('vmsoperation.tryeStoreManager.page.8','取消')}",
					handler : function() {
						detailWin.hide();
						detailForm.getForm().reset();
					}
				} ],
				items : [ detailForm ]
			});

			var treeLoader = new Ext.tree.TreeLoader(
					{
						dataUrl : "../vmscommon/deptTree.action?textField=deptName&idField=id&leafField=&clsField=&childrenField="
					});
			
			var treePanel = new Ext.tree.TreePanel({
				autoScroll : true,
				title : "${app:i18n_def('vmsoperation.tryeStoreManager.page.35','网点信息')}",
				width : 200,
				split : true,
				collapsible : true,
				region : 'west',
				loader : treeLoader,
				root : new Ext.tree.AsyncTreeNode({
					text : "${app:i18n_def('vmsoperation.tryeStoreManager.page.36','顺丰集团')}",
					id : "0"
				}),
				listeners : {
					click : function(node) {
						his_node = node;
						if(his_node.id != 0){
							onSearch();
						}
					}
				}
			});
			new Ext.Viewport({
				layout : 'border',
				items : [
						treePanel,
						{
							region : 'center',
							tbar : ['-'
							        <app:isPermission code="/vmsoperation/listTryeStores.action">,btnSearch</app:isPermission>
							        ,btnDetail
							        <app:isPermission code="/vmsoperation/saveTryeStore.action">,btnAdd</app:isPermission>
							        <app:isPermission code="/vmsoperation/updateTryeStore.action">,btnUpdate</app:isPermission> 
									,'->' 
									<app:isPermission code="/vmsoperation/exportTryeStore.action">,btnExport</app:isPermission> ],
							layout : 'border',

							items : [ queryForm, gridPanel ]
						} ]
			});
			function onSearch() {

				if (!his_node || his_node.id == 0) {
					Ext.MessageBox
							.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}",
									"${app:i18n_def('vmsoperation.tryeStoreManager.page.38','请选择所属网点')}");
					return;
				}

				queryForm.getForm().findField('tryeStore.deptId').setValue(
						his_node.id);

				if (queryForm.getForm().isValid() == false) {
					Ext.MessageBox
							.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}",
									"${app:i18n_def('vmsoperation.tryeStoreManager.page.39','数据校验错误！')}");
				} else {
					gridPanel.getStore().baseParams = queryForm.getForm()
							.getValues();
					gridPanel.getStore().baseParams["limit"] = pagingBar.pageSize;
					gridPanel.getStore().load();
				}
			}
			function onAdd() {
				if (!his_node || his_node.id == 0) {
					Ext.MessageBox
							.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}",
									"${app:i18n_def('vmsoperation.tryeStoreManager.page.38','请选择所属网点')}");
					return;
				}
				operator_type = 'add';
				editWin.setTitle("${app:i18n_def('vmsoperation.tryeStoreManager.page.25','入库')}");
				editWin.show();
				btnSave.enable();
				btnCancel.enable();
				editForm.getForm().reset();
			}
			function onUpdate() {
				operator_type = 'update';
				var records = gridPanel.getSelectionModel().getSelections();
				var record = gridPanel.getSelectionModel().getSelected();
				if (records.length == 1) {
					var tryeKind = record.get("tryeKind");
					if (tryeKind != '1') {
						Ext.Msg
								.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}",
										"${app:i18n_def('vmsoperation.tryeStoreManager.page.40','仅支持操作轮胎性质为新胎的数据')}");
						return;
					}
					editWin
							.setTitle("${app:i18n_def('people.edit.title','修改')}");
					editWin.show();
					btnSave.enable();
					btnCancel.enable();
					var obj = {};
					for (p in record.data) {
						if (p == 'tryeInTm') {
							editForm.getForm().findField('tryeStore.tryeInTm')
									.setValue(record.data[p].split('T')[0]);
							continue;
						}
						obj['tryeStore.' + p] = record.data[p]; 
					}
					editForm.getForm().setValues(obj);
					editForm.getForm().findField("tryeData").setValue(editForm.getForm().findField("tryeStore.tryeBreed").getValue()+","+editForm.getForm().findField("tryeStore.tryeModel").getValue());
				} else if (records.length < 1) {
					Ext.Msg
							.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}",
									"${app:i18n_def('vmsoperation.tryeStoreManager.page.41','请选择一笔数据行')}");
				} else if (records.length > 1) {
					Ext.Msg
							.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}",
									"${app:i18n_def('vmsoperation.tryeStoreManager.page.42','不能同时修改多条数据')}");
				}

			}
			function onExport() {
				if (!his_node || his_node.id == 0) {
					Ext.MessageBox
							.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}",
									"${app:i18n_def('vmsoperation.tryeStoreManager.page.38','请选择所属网点')}");
					return;
				}

				queryForm.getForm().doAction('submit', {
					waitTitle : "${app:i18n_def('vmsoperation.tryeStoreManager.page.43','请稍后')}",
					waitMsg : "${app:i18n_def('vmsoperation.tryeStoreManager.page.44','正在导出报表...')}",
					url : "exportTryeStore.action",
					success : showExportResult,
					failure : showSaveOrUpdateFailureInfo
				});
			}

			function onCancel() {
				editWin.hide();
			}

			function onSaveOrUpdate() {
				if (editForm.getForm().isValid() == false) {
					Ext.MessageBox
							.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}",
									"${app:i18n_def('vmsoperation.tryeStoreManager.page.39','数据校验错误！')}");
					return;
				}
				//入库时间与当前时间比较
				var tryeInTm = editForm.getForm().findField("tryeStore.tryeInTm").getValue() ;
				if(tryeInTm > new Date()){
					Ext.MessageBox
					.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}",
							"${app:i18n_def('vmsoperation.tryeStoreManager.page.tryeInTm.1','入库时间不能大于当前时间')}");
					return ;
				}
				
				//校验 当品牌型号为‘其他型号’时，必须填写备注   
				var tryeModel = editForm.getForm().findField(
						"tryeStore.tryeModel").getValue();
				if (tryeModel == '${app:i18n_def('vmsoperation.tryeStoreManager.page.45','其他型号')}') {
					var remark = editForm.getForm().findField(
							"tryeStore.remark").getValue();
					if (remark.trim() == '') {
						Ext.MessageBox
								.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}",
										"${app:i18n_def('vmsoperation.tryeStoreManager.page.46','请填写备注！')}");
						return;
					}
				}
				//校验轮胎编码、验收人是否为空串
				var tryeNo = editForm.getForm().findField('tryeStore.tryeNo').getValue();
				if(tryeNo.trim() == ''){
					Ext.MessageBox.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}", "${app:i18n_def('vmsoperation.tryeStoreManager.page.60','轮胎编码不能为空')}");
					return ;
				}
				if(tryeNo.trim().length <10 || tryeNo.trim().length > 11){
					Ext.MessageBox.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}", "${app:i18n_def('vmsoperation.tryeStoreManager.page.62','轮胎编码格式不匹配,轮胎编码长度必须大于等于10，小于等于11')}");
					return ;
				}
				
				if(editForm.getForm().findField('tryeStore.checkEmp').getValue().trim() == ''){
					Ext.MessageBox.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}", "${app:i18n_def('vmsoperation.tryeStoreManager.page.61','验收人不能为空')}");
					return ;
				}
				var waitMsg;
				if (operator_type == 'add') {
					editForm.getForm().findField('tryeStore.deptId').setValue(
							his_node.id);
					waitMsg = "${app:i18n_def('vmsoperation.tryeStoreManager.page.47','正在执行保存操作...')}";
					editForm.getForm().url = "saveTryeStore.action";
				} else if (operator_type == 'update') {
					waitMsg = "${app:i18n_def('vmsoperation.tryeStoreManager.page.48','正在执行修改操作...')}";
					editForm.getForm().url = "updateTryeStore.action";
				}
				editForm
						.getForm()
						.submit(
								{
									success : showSaveOrUpdateSuccessInfo,
									failure : showSaveOrUpdateFailureInfo,
									waitMsg : waitMsg,
									waitTitle : "${app:i18n_def('vmsoperation.tryeStoreManager.page.49','请稍后...')}"
								});
			}

			function showSaveOrUpdateSuccessInfo(form, action) {
				if (action.result.msg) {
					Ext.Msg.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}",
							action.result.msg);
				} else {
					if (operator_type == 'add') {
						Ext.Msg.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}",
								"${app:i18n_def('vmsoperation.tryeStoreManager.page.50','保存成功')}");
					} else if (operator_type == 'update') {
						Ext.Msg
								.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}",
										"${app:i18n_def('vmsoperation.tryeStoreManager.page.51','更新成功')}");
					}
					editWin.hide();
					gridStore.reload();
				}
			}

			function showSaveOrUpdateFailureInfo(form, action) {
				var msg = '';
				if (action && action.result && action.result.error) {
					Ext.Msg
							.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}",
									"${app:i18n_def('vmsoperation.tryeStoreManager.page.52','系统出现了异常,请与管理员联系!')}");
				} else {
					if (action.failureType === Ext.form.Action.CONNECT_FAILURE) {
						if (action.response.status == 0) {
							msg = "${app:i18n_def('vmsoperation.tryeStoreManager.page.53','连接失败,无法连接到服务器!')}";
						} else if (action.response.status == -1) {
							msg = "${app:i18n_def('vmsoperation.tryeStoreManager.page.54','服务器处理超时!')}";
						} else {
							msg = String
									.format(
											"${app:i18n_def('vmsoperation.tryeStoreManager.page.55','错误代码:{0}, 错误描述:{1}!')}",
											action.response.status,
											action.response.statusText);
						}
					} else if (action.failureType === Ext.form.Action.CLIENT_INVALID) {
						msg = "${app:i18n_def('vmsoperation.tryeStoreManager.page.56','请将表单填写正确!')}";
					}
					Ext.Msg.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}", msg);
				}
			}
			//导出成功处理事件
			  function showExportResult(form, action) {
					var fileName = action.result.fileName;
					var url = "downloadTryeStoreExcelFile.action?fileName=" + encodeURI(encodeURI(fileName));
					window.location = url;
				}
			function onDetail() {
				var records = gridPanel.getSelectionModel().getSelections();
				var record = gridPanel.getSelectionModel().getSelected();
				if (records.length == 1) {
					detailWin
							.setTitle("${app:i18n_def('vmsoperation.tryeStoreManager.page.57','查看明细')}");
					detailWin.show();
					var obj = {};

					for (p in record.data) {
						if (p == 'tryeInTm') {
							detailForm.getForm()
									.findField('tryeStore.tryeInTm').setValue(
											record.data[p].split('T')[0]);
							continue;
						}

						obj['tryeStore.' + p] = record.data[p];
					}
					detailForm.getForm().setValues(obj);
				} else if (records.length < 1) {
					Ext.Msg
							.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}",
									"${app:i18n_def('vmsoperation.tryeStoreManager.page.58','请选择一条记录')}");
				} else if (records.length > 1) {
					Ext.Msg
							.alert("${app:i18n_def('vmsoperation.tryeStoreManager.page.37','提示')}",
									"${app:i18n_def('vmsoperation.tryeStoreManager.page.59','不能同时操作多条记录')}");
				}
			}

		});