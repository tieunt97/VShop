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
Route::get('providers','ProviderController@getAllProviders');

Route::get('products/{id}','ProductController@getProductById')->name('product');
Route::get('products/{id}/allValuations','ProductController@getEvaluationsOfProductId');
Route::get('products/{id}/getRestOfAmount','ProductController@getRestOfProductsInStock');
// Route::get('product_type/{productTypeId}/filter/products','HomeController@search');
Route::get('sort&filter/products','HomeController@sortAndFilterProduct');


Route::group(['middleware'	=>	'auth'], function() {
	Route::get('logout','Auth\LoginController@logout');
});

Route::get('products/search/{keyword}','ProductController@searchProductBy');
Route::get('sale_bills/pending','SaleBillController@getSaleBillisPending');
Route::get('sale_bills/{saleId}','SaleBillController@getSaleBillDescriptionBySaleBillId');

// Route::get('test','HomeController@test');

Auth::routes();

Route::get('/home', 'HomeController@index')->name('home');
