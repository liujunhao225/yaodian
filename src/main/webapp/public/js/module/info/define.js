//model start
Ext.define('infoModel', {
	extend : 'Ext.data.Model',
	fields : [{
		name : 'medicineId',
		type : 'string'
	}, {
		name : 'medicineBarCode',
		type : 'string'
	}, {
		name : 'medicineBasePrice',
		type : 'float'
	}, {
		name : 'medicineName',
		type : 'string'
	}, {
		name : 'medicineSellsPrice',
		type : 'float'
	}]
});

