<?php

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

Route::get('/', function () {
    return view('welcome');
});

Route::get('product_type','HomeController@getAllProductType')->name('types');
Route::get('product_type/{productTypeId}/products','HomeController@getProductByType');
Route::get('product_provider/{providerId}/products', 'HomeController@getProductByProvider');
Route::get('providers','ProviderController@getAllProviders');

Route::get('products/{id}','ProductController@getProductById')->name('product');
Route::get('products/search/{keyword}','ProductController@searchProductBy');
Route::get('sale_bills/pending','SaleBillController@getSaleBillisPending');


Route::get('test','SaleBillController@getSaleBillisPending');
