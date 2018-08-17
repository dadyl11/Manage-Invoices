package restassured;

import pl.coderstrust.accounting.helpers.InvoiceProvider;
import pl.coderstrust.accounting.model.Invoice;

public interface Data {

  Invoice invoiceKrakow = InvoiceProvider.INVOICE_KRAKOW_2018;
  Invoice invoiceBydgoszcz = InvoiceProvider.INVOICE_BYDGOSZCZ_2018;
  Invoice invoiceRadomsko = InvoiceProvider.INVOICE_RADOMSKO_2018;
  Invoice invoiceGudziadz = InvoiceProvider.INVOICE_GRUDZIADZ_2017;
  Invoice invoiceChelmno = InvoiceProvider.INVOICE_CHELMNO_2016;

  String nipCHse = invoiceChelmno.getSeller().getNip();
  String nipBYse = invoiceBydgoszcz.getSeller().getNip();
  String nipKRse = invoiceKrakow.getSeller().getNip();
  String nipGRse = invoiceGudziadz.getSeller().getNip();
  String nipRAse = invoiceRadomsko.getSeller().getNip();
  String nipCHba = invoiceChelmno.getBuyer().getNip();
  String nipBYba = invoiceBydgoszcz.getBuyer().getNip();
  String nipKRba = invoiceKrakow.getBuyer().getNip();
  String nipGRba = invoiceGudziadz.getBuyer().getNip();
  String nipRAba = invoiceRadomsko.getBuyer().getNip();

  String invoicesUrl = "/invoices";
}
