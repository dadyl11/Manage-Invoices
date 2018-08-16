package restassured;

import pl.coderstrust.accounting.helpers.InvoiceProvider;
import pl.coderstrust.accounting.model.Invoice;

public interface Data {

  Invoice invoiceKR = InvoiceProvider.INVOICE_KRAKOW_2018;
  Invoice invoiceBY = InvoiceProvider.INVOICE_BYDGOSZCZ_2018;
  Invoice invoiceRA = InvoiceProvider.INVOICE_RADOMSKO_2018;
  Invoice invoiceGR = InvoiceProvider.INVOICE_GRUDZIADZ_2017;
  Invoice invoiceCH = InvoiceProvider.INVOICE_CHELMNO_2016;

  String nipCHse = invoiceCH.getSeller().getNip();
  String nipBYse = invoiceBY.getSeller().getNip();
  String nipKRse = invoiceKR.getSeller().getNip();
  String nipGRse = invoiceGR.getSeller().getNip();
  String nipRAse = invoiceRA.getSeller().getNip();
  String nipCHba = invoiceCH.getBuyer().getNip();
  String nipBYba = invoiceBY.getBuyer().getNip();
  String nipKRba = invoiceKR.getBuyer().getNip();
  String nipGRba = invoiceGR.getBuyer().getNip();
  String nipRAba = invoiceRA.getBuyer().getNip();

  String invoicesUrl = "/invoices";
}
