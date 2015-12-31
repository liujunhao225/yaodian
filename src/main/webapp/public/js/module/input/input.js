/**
 * medicineInfo
 */
var PAGE_SIZE = 20;
var INPUTLOG_SAVE_URL = "/oscar/input/add.do";
var INPUTLOG_UPDATE_URL = "/oscar/input/update.do";
var INPUTLOG_DELETE_URL="/oscar/input/delete.do";
var INPUTLOG_ADDONE_URL="/oscar/input/addone.do";
var INPUTLOG_FINDMEDICINE_URL="/oscar/input/findone.do";
Ext.onReady(function() {
    Ext.QuickTips.init();
    var inputStore = Ext.create('Ext.data.Store', {
        model: 'inputModel'
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
               if(tempdata.id==''||tempdata.id=='undefined'||tempdata.id==0){
            	   requestUrl= INPUTLOG_SAVE_URL;
               }else{
            	   requestUrl= INPUTLOG_UPDATE_URL;
               }
                Ext.Ajax.request({
                    url: requestUrl,
                    params: {
                    	'medicineId':tempdata.medicineId,
                    	'strCount':tempdata.leftNum,
                    	'id':tempdata.id
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
        store: inputStore ,
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
            dataIndex: 'id',
            hidden:true
        },
        {
        	dataIndex:'medicineId',
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
        		blankText:'药品名称必须填'
        	}
        },
        {
            text: '数量',
            dataIndex: 'leftNum',
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
                                url: INPUTLOG_DELETE_URL,
                                params: {
                                	medicineid: model.data.medicineId
                                },
                                success: function(response) {
                                	_store.remove(rowIndex);
                                },
                                failure: function() {
                                    Ext.Msg.alert('操作提示', "删除失败！");
                                }
                            });
                        }
            }
           ]
        }],
        dockedItems: [
        {
            xtype: 'toolbar',
            dock: 'top',
            items: [
                    '条码',{
                    	xtype:'textfield',id:'tbarcode',width:120,
                    	listeners:{
                    		'change':function(item,newValue,oldValue,e){
                    			if(newValue !=null && newValue !=''){
                    			var existFlag = false;
                    			inputStore.each(function(record) {
                    			   if(record.get('medicineBarCode')==newValue){
//                    				   record.set('leftNum',record.get('leftNum')+1);
                    				   existFlag = true;
                    			   }
                    			});
                    			if(existFlag==false){
        	            				Ext.Ajax.request({
        	            					url: INPUTLOG_FINDMEDICINE_URL,
        	            					params: { 
        	            						barCode:newValue
        	            					},
        	            					success: function(response){
        	            				        var text = response.responseText;
        	            				        console.log(text);
        	            				        var r = Ext.JSON.decode(text);
        	            				        inputStore.insert(0, r);
        	            				    }
        	            			});
                    			
                    		}
                    		item.reset();	
                    	}
                    	}
                    }
                    }
                  ]
        }]
    });
   
    
});