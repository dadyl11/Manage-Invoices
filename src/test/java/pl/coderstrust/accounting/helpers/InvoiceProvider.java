package pl.coderstrust.accounting.helpers;

import static pl.coderstrust.accounting.helpers.InvoiceEntryProvider.EMPTY_DESCRIPTION;
import static pl.coderstrust.accounting.helpers.InvoiceEntryProvider.EMPTY_NET_PRICE;
import static pl.coderstrust.accounting.helpers.InvoiceEntryProvider.EMPTY_QUANTITY;
import static pl.coderstrust.accounting.helpers.InvoiceEntryProvider.EMPTY_VAT_RATE;
import static pl.coderstrust.accounting.helpers.InvoiceEntryProvider.ONE_LINK;
import static pl.coderstrust.accounting.helpers.InvoiceEntryProvider.SPAN_CLAMP;
import static pl.coderstrust.accounting.helpers.InvoiceEntryProvider.SPAN_CLAMP_SUPPORT;

import java.time.LocalDate;
import java.util.Collections;
import pl.coderstrust.accounting.model.Invoice;

public class InvoiceProvider {

  public static final Invoice INVOICE_KRAKOW_2018 = new Invoice.Invoicebuilder()
      .identifier("1/2018")
      .issueDate(LocalDate.of(2018, 5, 12))
      .saleDate(LocalDate.of(2018, 5, 12))
      .salePlace("Krakow")
      .buyer(CompanyProvider.COMPANY_TRANSPOL)
      .seller(CompanyProvider.COMPANY_DRUTEX)
      .entries(SPAN_CLAMP_SUPPORT)
      .build();


  // TODO I don't like your naming convention - is the city the most important aspect of each invoice? Do you know by sale place what is inside?
  public static final Invoice INVOICE_GRUDZIADZ_2017 = new Invoice.Invoicebuilder()
      .identifier("2/2018")
      .issueDate(LocalDate.of(2017, 3, 3))
      .saleDate(LocalDate.of(2017, 3, 2))
      .salePlace("Grudziadz")
      .buyer(CompanyProvider.COMPANY_DRUKPOL)
      .seller(CompanyProvider.COMPANY_WASBUD)
      .entries(SPAN_CLAMP)
      .build();

  public static final Invoice INVOICE_CHELMNO_2016 = new Invoice.Invoicebuilder()
      .identifier("3/2018")
      .issueDate(LocalDate.of(2016, 3, 5))// TODO please use more various dates
      .saleDate(LocalDate.of(2017, 3, 2))
      .salePlace("Grudziadz")
      .buyer(CompanyProvider.COMPANY_DRUKPOL)
      .seller(CompanyProvider.COMPANY_TRANSPOL)
      .entries(SPAN_CLAMP_SUPPORT)
      .build();

  public static final Invoice INVOICE_BYDGOSZCZ_2018 = new Invoice.Invoicebuilder()
      .identifier("4/2018")
      .issueDate(LocalDate.of(2018, 06, 13))// TODO please use more various dates
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Bydgoszcz")
      .buyer(CompanyProvider.COMPANY_WASBUD)
      .seller(CompanyProvider.COMPANY_DRUTEX)
      .entries(ONE_LINK)
      .build();

  public static final Invoice INVOICE_RADOMSKO_2018 = new Invoice.Invoicebuilder()
      .identifier("ss999")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Radomsko")
      .buyer(CompanyProvider.COMPANY_DRUTEX)
      .seller(CompanyProvider.COMPANY_WASBUD)
      .entries(ONE_LINK)
      .build();

  public static final Invoice INVOICE_EMPTY_ENTRIES = new Invoice.Invoicebuilder()
      .identifier("555/mm")
      .issueDate(LocalDate.of(2018, 02, 12))// TODO please use more various dates
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("LODZ")
      .buyer(CompanyProvider.COMPANY_WASBUD)
      .seller(CompanyProvider.COMPANY_DRUTEX)
      .entries(Collections.emptyList())
      .build();

  public static final Invoice INVOICE_BLANK_SALE_PLACE = new Invoice.Invoicebuilder()
      .identifier("4/2018")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("")
      .buyer(CompanyProvider.COMPANY_WASBUD)
      .seller(CompanyProvider.COMPANY_DRUTEX)
      .entries(ONE_LINK)
      .build();

  public static final Invoice INVOICE_BLANK_IDENTIFIER = new Invoice.Invoicebuilder()
      .identifier("")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Lodz")
      .buyer(CompanyProvider.COMPANY_WASBUD)
      .seller(CompanyProvider.COMPANY_DRUTEX)
      .entries(ONE_LINK)
      .build();

  public static final Invoice INVOICE_BLANK_ISSUE_DATE = new Invoice.Invoicebuilder()
      .identifier("555/mm")
      .issueDate(null)
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Lodz")
      .buyer(CompanyProvider.COMPANY_WASBUD)
      .seller(CompanyProvider.COMPANY_DRUTEX)
      .entries(ONE_LINK)
      .build();

  public static final Invoice INVOICE_BLANK_SALE_DATE = new Invoice.Invoicebuilder()
      .identifier("555/mm")
      .issueDate(LocalDate.of(2018, 05, 12))
      .saleDate(null)
      .salePlace("Lodz")
      .buyer(CompanyProvider.COMPANY_WASBUD)
      .seller(CompanyProvider.COMPANY_DRUTEX)
      .entries(ONE_LINK)
      .build();

  public static final Invoice INVOICE_BLANK_BUYER_NAME = new Invoice.Invoicebuilder()
      .identifier("555/mm")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Lodz")
      .buyer(CompanyProvider.COMPANY_BLANK_NAME)
      .seller(CompanyProvider.COMPANY_DRUTEX)
      .entries(ONE_LINK)
      .build();

  public static final Invoice INVOICE_BLANK_BUYER_NIP = new Invoice.Invoicebuilder()
      .identifier("ss999")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Lodz")
      .buyer(CompanyProvider.COMPANY_BLANK_NIP)
      .seller(CompanyProvider.COMPANY_DRUTEX)
      .entries(ONE_LINK)
      .build();

  public static final Invoice INVOICE_BLANK_BUYER_STREET = new Invoice.Invoicebuilder()
      .identifier("ss999")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Lodz")
      .buyer(CompanyProvider.COMPANY_BLANK_STREET)
      .seller(CompanyProvider.COMPANY_DRUTEX)
      .entries(ONE_LINK)
      .build();

  public static final Invoice INVOICE_BLANK_BUYER_POSTAL_CODE = new Invoice.Invoicebuilder()
      .identifier("ss999")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Lodz")
      .buyer(CompanyProvider.COMPANY_BLANK_POSTAL_CODE)
      .seller(CompanyProvider.COMPANY_DRUTEX)
      .entries(ONE_LINK)
      .build();

  public static final Invoice INVOICE_BLANK_BUYER_CITY = new Invoice.Invoicebuilder()
      .identifier("ss999")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Lodz")
      .buyer(CompanyProvider.COMPANY_BLANK_CITY)
      .seller(CompanyProvider.COMPANY_DRUTEX)
      .entries(ONE_LINK)
      .build();

  public static final Invoice INVOICE_BLANK_BUYER_DISCOUNT = new Invoice.Invoicebuilder()
      .identifier("ss999")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Lodz")
      .buyer(CompanyProvider.COMPANY_BLANK_DISCOUNT)
      .seller(CompanyProvider.COMPANY_DRUTEX)
      .entries(ONE_LINK)
      .build();

  public static final Invoice INVOICE_BLANK_SELLER_NAME = new Invoice.Invoicebuilder()
      .identifier("ss999")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Lodz")
      .buyer(CompanyProvider.COMPANY_DRUTEX)
      .seller(CompanyProvider.COMPANY_BLANK_NAME)
      .entries(ONE_LINK)
      .build();

  public static final Invoice INVOICE_BLANK_SELLER_NIP = new Invoice.Invoicebuilder()
      .identifier("ss999")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Lodz")
      .buyer(CompanyProvider.COMPANY_DRUTEX)
      .seller(CompanyProvider.COMPANY_BLANK_NIP)
      .entries(ONE_LINK)
      .build();

  public static final Invoice INVOICE_BLANK_SELLER_STREET = new Invoice.Invoicebuilder()
      .identifier("ss999")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Lodz")
      .buyer(CompanyProvider.COMPANY_DRUTEX)
      .seller(CompanyProvider.COMPANY_BLANK_STREET)
      .entries(ONE_LINK)
      .build();


  public static final Invoice INVOICE_BLANK_SELLER_POSTAL_CODE = new Invoice.Invoicebuilder()
      .identifier("ss999")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Lodz")
      .buyer(CompanyProvider.COMPANY_DRUTEX)
      .seller(CompanyProvider.COMPANY_BLANK_POSTAL_CODE)
      .entries(ONE_LINK)
      .build();

  public static final Invoice INVOICE_BLANK_SELLER_CITY = new Invoice.Invoicebuilder()
      .identifier("ss999")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Lodz")
      .buyer(CompanyProvider.COMPANY_DRUTEX)
      .seller(CompanyProvider.COMPANY_BLANK_CITY)
      .entries(ONE_LINK)
      .build();

  public static final Invoice INVOICE_BLANK_SELLER_DISCOUNT = new Invoice.Invoicebuilder()
      .identifier("ss999")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Lodz")
      .buyer(CompanyProvider.COMPANY_DRUTEX)
      .seller(CompanyProvider.COMPANY_BLANK_DISCOUNT)
      .entries(ONE_LINK)
      .build();


  public static final Invoice INVOICE_BLANK_ENTRY_DESCRIPTION = new Invoice.Invoicebuilder()
      .identifier("ss999")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Lodz")
      .buyer(CompanyProvider.COMPANY_DRUTEX)
      .seller(CompanyProvider.COMPANY_WASBUD)
      .entries(EMPTY_DESCRIPTION)
      .build();

  public static final Invoice INVOICE_BLANK_ENTRY_NET_PRICE = new Invoice.Invoicebuilder()
      .identifier("ss999")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Lodz")
      .buyer(CompanyProvider.COMPANY_DRUTEX)
      .seller(CompanyProvider.COMPANY_WASBUD)
      .entries(EMPTY_NET_PRICE)
      .build();

  public static final Invoice INVOICE_BLANK_ENTRY_VAT_RATE = new Invoice.Invoicebuilder()
      .identifier("ss999")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Lodz")
      .buyer(CompanyProvider.COMPANY_DRUTEX)
      .seller(CompanyProvider.COMPANY_WASBUD)
      .entries(EMPTY_VAT_RATE)
      .build();

  public static final Invoice INVOICE_BLANK_ENTRY_QUNTITY = new Invoice.Invoicebuilder()
      .identifier("ss999")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Lodz")
      .buyer(CompanyProvider.COMPANY_DRUTEX)
      .seller(CompanyProvider.COMPANY_WASBUD)
      .entries(EMPTY_QUANTITY)
      .build();

  public static final Invoice INVOICE_BAD_DISCOUNT_VALUE = new Invoice.Invoicebuilder()
      .identifier("ss999")
      .issueDate(LocalDate.of(2018, 06, 13))
      .saleDate(LocalDate.of(2018, 05, 12))
      .salePlace("Radomsko")
      .buyer(CompanyProvider.COMPANY_DRUTEX)
      .seller(CompanyProvider.COMPANY_DISCOUNT_BIGGER_THAN_1)
      .entries(ONE_LINK)
      .build();
}
