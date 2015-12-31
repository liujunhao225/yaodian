/**
 * medicineInfo
 */
var PAGE_SIZE = 20;
var PRODUCT_STORE_URL = "/oscar/info/list.do";
var PRODUCT_SAVE_URL = "/oscar/info/add.do";
var PRODUCT_UPDATE_URL = "/oscar/info/edit.do";
var PRODUCT_DELETE_URL = "/oscar/info/delete.do";
Ext.onReady(function() {
    Ext.QuickTips.init();
    var infoStroe = Ext.create('Ext.data.Store', {
        model: 'infoModel',
        pageSize: PAGE_SIZE,
        proxy: {
            type: 'ajax',
            url: PRODUCT_STORE_URL,
            reader: {
                type: 'json',
                root: 'datalist',
                totalProperty: 'totalRecords'
            }
        }
    });
    infoStroe .load({
        params: {
            start: 0,
            limit: PAGE_SIZE
        }
    });
    
    var rowEditing2 =  Ext.create('Ext.grid.plugin.RowEditing', {
        clicksToMoveEditor: 1,
        autoCancel: false,
        saveBtnText  : '保存',
        cancelBtnText: '取消',
        pluginId:'rowediting22',
        listeners:{
        	edit: function(editor, ctx, eOpts) {
               var tempdata = ctx.record.data;
               var requestUrl='';
               if(tempdata.medicineId==''||tempdata=='undefined'){
            	   requestUrl= PRODUCT_SAVE_URL;
               }else{
            	   requestUrl= PRODUCT_UPDATE_URL;
               }
                Ext.Ajax.request({
                    url: requestUrl,
                    params: { 
                    	'medicineId':tempdata.medicineId,
                    	'medicineName':tempdata.medicineName,
                    	'medicineBarCode':tempdata.medicineBarCode,
                    	'basePrice':tempdata.medicineBasePrice,
                    	'sellsPrice':tempdata.medicineSellsPrice,
                    },
                    success: function(response){
                    	
                    }
                });
            },
            canceledit:function(rowEditing, context) {
            	
            }
        }
    });
    var infoGrid = Ext.create('Ext.grid.Panel', {
        store: infoStroe ,
        region: 'south',
        renderTo: 'gridpanel',
//        autoHeight:true,
        height:800,
        forceFit: 1,
        plugins:[
                 rowEditing2
                ],
        viewConfig: {
            emptyText: '&nbsp;&nbsp;没有相关的记录'
        },
        columns: [
        {
            dataIndex: 'medicineId',
            hidden:true
        },
        {
            text: '药品名称',
            dataIndex: 'medicineName',
            editor:{
              	 allowBlank: true,
              	 blankText:'药品名称必须填'
            }
        },
        {
            text: '条码',
            dataIndex: 'medicineBarCode',
            editor:{
               allowBlank: true,
                  	 blankText:'条码必须填'
             }
        },
        {
            text: '进价',
            dataIndex: 'medicineBasePrice',
            	editor:{
                    allowBlank: true,
                       	 blankText:'条码必须填'
                  }
        },
        {
            text: '销售价',
            dataIndex: 'medicineSellsPrice',
            editor:{
                allowBlank: true,
                   	 blankText:'销售价必须填'
              }
          
        }, {
            header: '操作',
            menuDisabled: true,
            sortable: false,
            xtype: 'actioncolumn',
            items: [{
                icon: '/oscar/public/images/common/delete.gif',
                tooltip: '删除',
                handler: function(grid, rowIndex, colIndex) {
                            var _store = grid.getStore();
                            var model = grid.getStore().getAt(rowIndex);
                            Ext.Ajax.request({
                                url: PRODUCT_DELETE_URL,
                                params: {
                                	medicineId: model.data.medicineId
                                },
                                success: function(response) {
                                    _store.loadPage(_store.currentPage);
                                },
                                failure: function() {
                                    Ext.Msg.alert('操作提示', "删除失败！");
                                }
                            });
                        }
            }
           ]
        }],
        dockedItems: [{
            dock: 'bottom',
            xtype: 'pagingtoolbar',
            store: infoStroe ,
            displayInfo: true,
            refreshText: '刷新',
            firstText: '第一页',
            prevText: '上一页',
            nextText: '下一页',
            lastText: '尾页',
            beforePageText: '跳转到第',
            afterPageText: '页,共{0}页',
            displayMsg: '第{0} - {1}条记录,共 {2}条记录',
            emptyMsg: '没有记录'
        },
        {
            xtype: 'toolbar',
            dock: 'top',
            items: [
                    '条码',{
                    	xtype:'textfield',id:'tbarcode',width:120
                    },
                    '药品名称',{
                    	xtype:'textfield',id:'tmedicineName',width:120
                    },{
    					xtype : 'button',
    					text : '查询',
    					icon : '/oscar/public/images/common/icon_searchd.gif',
    					listeners:{
    						click:function(){
    							if(infoStroe ){
    								var proxy = infoStroe.getProxy();
    								proxy.setExtraParam('medicineName',Ext.getCmp('tmedicineName').getValue());
    								proxy.setExtraParam('medicineBarCode',Ext.getCmp('tbarcode').getValue());
    								infoStroe.load({
    									params:{
    										start:0,
    										limit:PAGE_SIZE
    									}
    								});
    							}
    						}
    					}
    				},'->',{
    	                text: '增加',
    	                iconCls: 'icon-add',
    	                handler: function(){
    	                    // empty record
    	                	infoStroe.insert(0, new infoModel());
    	                	rowEditing2.startEdit(0, 0);
    	                }
    	            	}],
        }]
    });
   
    
});