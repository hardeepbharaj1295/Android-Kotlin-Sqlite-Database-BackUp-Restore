package com.example.gabrieldatabase.projectDatabase

object DatabaseColumns
{
    const val TABLE_STOCK_AVAILABLE           = "Table1"
    const val STOCK_PART_NUMBER               = "PartNumber"
    const val STOCK_PART_NAME                 = "PartName"
    const val STOCK_STOCK_AVAILABLE           = "StockAvailable"
    const val STOCK_ORDER_UNIT                = "OrderUnit"
    const val STOCK_VENDOR_PLANT              = "VendorPlant"
    const val STOCK_NET_PRICE                 = "NetPrice"
    const val STOCK_TOTAL_VALUE               = "TotalValue"

    const val TABLE_PBR_INWARD                 = "Table2"
    const val INWARD_LR_NUMBER	               = "LrNumber"
    const val INWARD_INVOICE_NUMBER            = "InvoiceNumber"
    const val INWARD_AVAILABLE_OR_NOT          = "AvailableOrNot"
    const val INWARD_DATE                      = "Date"
    const val INWARD_PART_NO                   = "PartNo"
    const val INWARD_QUANTITY                  = "Quantity"
    const val INWARD_PKG                       = "PKG"
    const val INWARD_PARTS_NAME                = "PartName"
    const val INWARD_PRICE_PER_UNIT            = "PricePerUnit"
    const val INWARD_VALUE_OF_INVOICE          = "ValueOfInvoice"
    const val INWARD_REQ                       = "req"
    const val INWARD_DC_NUMBER                 = "DCNumber"
    const val INWARD_VENDORS_NAME              = "VendorsName"

    const val TABLE_PBR_OUTWARD                 = "Table3"
    const val OUTWARD_LR_NUMBER                 = "LRNumber"
    const val OUTWARD_DATE                      = "Date"
    const val OUTWARD_PART_NO                   = "PartNo"
    const val OUTWARD_PART_NAME                 = "PartName"
    const val OUTWARD_QUANTITY                  = "Quantity"
    const val OUTWARD_PKG                       = "PKG"
    const val OUTWARD_INVOICE_NUMBER            = "InvoiceNumber"
    const val OUTWARD_DC_NUMBER                 = "DCNumber"
    const val OUTWARD_VENDORS_NAME              = "VendorsName"

    const val TABLE_MONITORING                  = "TABLE4"
    const val MON_PART_NAME                     = "PartName"
    const val MON_PART_NUMBER                   = "PartNumber"
    const val MON_QTY_INWARDED                  = "QtyInwarded"
    const val MON_QTY_OUTWARDED                 = "QtyOutwarded"

    const val TABLE_SG_CONNECTIONS              = "Table5"
    const val SG_LR_NUMBER                      = "LRNumber"
    const val SG_AVAILABLE_OR_NOT               = "AvailableOrNot"
    const val SG_DATE                           = "Date"
    const val SG_PART_NO                        = "PartNo"
    const val SG_QUANTITY                       = "Quantity"
    const val SG_PKG                            = "PKG"
    const val SG_PART_NAME                      = "PartName"
    const val SG_PRICE_PER_UNIT                 = "PricePerUnit"
    const val SG_VALUE_OF_INVOICE               = "ValueOfInvoice"
    const val SG_REQ                            = "Req"
    const val SG_INVOICE_NUMBER                 = "InvoiceNumber"
    const val SG_DC_NUMBER                      = "DCNumber"
    const val SG_VENDORS_NAME                   = "VendorsName"


    const val TABLE_SIGNUP                      = "Table6"
    const val SU_NAME                           = "Name"
    const val SU_EMAIL                          = "Email"
    const val SU_PHONE                          = "Phone_Number"
    const val SU_PASSWORD                       = "Password"
}