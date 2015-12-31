/**
 * medicineInfo
 */
var PAGE_SIZE = 20;
var INPUTLOG_LIST_URL = "/oscar/summary/list.do";
Ext.onReady(function() {
    Ext.QuickTips.init();
    var inputStore = Ext.create('Ext.data.Store', {
        model: 'summaryModel',
        pageSize:PAGE_SIZE,
		proxy:{
			type:'ajax',
			url:INPUTLOG_LIST_URL,
			reader:{
				type:'json',
				root:'datalist',
				totalProperty:'totalRecords'
			}
		}
    });
    inputStore.load({
		params:{
			start:0,
			limit:PAGE_SIZE
		}
	});
    
    var infoGrid = Ext.create('Ext.grid.Panel', {
        store: inputStore ,
        region: 'south',
        renderTo: 'gridpanel',
        height:800,
        forceFit: 1,
        viewConfig: {
            emptyText: '&nbsp;&nbsp;没有相关的记录'
        },
        columns: [
        {
        	text:'销售额',
            dataIndex: 'summoney',
        },
        {
        	dataIndex:'sellsDay',
        	text:'日期',
        }],
        dockedItems: [{
	    	   dock:'bottom',
	    	   xtype:'pagingtoolbar',
	    	   store:inputStore,
	    	   displayInfo:true,
	    	   refreshText:'刷新',
	    	   firstText:'第一页',
	    	   prevText:'上一页',
	    	   nextText:'下一页',
	    	   lastText:'尾页',
	    	   beforePageText:'跳转到第',
	    	   afterPageText:'页,共{0}页',
	    	   displayMsg:'第{0} - {1}条记录,共 {2}条记录',
	    	   emptyMsg:'没有记录'
	    }]
    });
   
    
});