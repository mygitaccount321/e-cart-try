import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../common/product';
import { map } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { ProductCategory } from '../common/product-category';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = 'http://localhost:8080/api/products';
  private baseUrl1 = 'http://localhost:8080/api/product';

  private categoryUrl = 'http://localhost:8080/api/product-category';
  constructor(private httpClient: HttpClient) { }

  getProduct(theProductId: number): Observable<Product> {

    // need to build URL based on product id
    const productUrl = `${this.baseUrl1}?id=${theProductId}`;

    return this.httpClient.get<Product>(productUrl);
  }
  getProductList(theCategoryId: number): Observable<Product[]> {
    const searchUrl = `${this.baseUrl}/search/findByCategoryId?id=${theCategoryId}`;
    return this.httpClient.get<Product[]>(searchUrl).pipe(
      map(response => {
        console.log(response);
         return response;
        })
    );
  }

  searchProducts(theKeyword: string): Observable<Product[]> {

    const searchUrl = `${this.baseUrl}/search/findByNameContaining?name=${theKeyword}`;

    return this.getProducts(searchUrl);
  }

  private getProducts(searchUrl: string): Observable<Product[]> {
    return this.httpClient.get<Product[]>(searchUrl).pipe(map(response => response));
  }
  getProductCategories(): Observable<ProductCategory[]> {

    return this.httpClient.get<ProductCategory[]>(this.categoryUrl).pipe(
      map(response => response)
    );
  }

  getProductListPaginate(thePage: number, 
    thePageSize: number, 
    theCategoryId: number): Observable<GetResponseProducts> {

// need to build URL based on category id, page and size 
const searchUrl = `${this.baseUrl}/search/findByCategoryIdAndPage?id=${theCategoryId}`
+ `&page=${thePage}&size=${thePageSize}`;

return this.httpClient.get<GetResponseProducts>(searchUrl);
}

    searchProductsPaginate(thePage: number, 
        thePageSize: number, 
        theKeyword: string): Observable<GetResponseProducts> {

      // need to build URL based on keyword, page and size 
      const searchUrl = `${this.baseUrl}/search/findByNameContainingAndPage?name=${theKeyword}`
      + `&page=${thePage}&size=${thePageSize}`;

      return this.httpClient.get<GetResponseProducts>(searchUrl);
      }
}

interface GetResponseProducts {

  productDto: Product[];
  pageDto: {
    size: number,
    totalElements: number,
    totalPages: number,
    number: number
  }
}
interface GetResponse {
    products: Product[];
}