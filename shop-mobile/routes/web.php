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
Route::get('{productTypeId}/products','HomeController@getProductByType');

Route::get('products/{id}','ProductController@getProductById')->name('product');
Route::get('products/search/{keyword}','ProductController@searchProductBy');