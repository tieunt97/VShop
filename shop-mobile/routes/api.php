<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

Route::group(['middleware' => 'auth:api'], function() {
	Route::post('user/update','UsersController@updateUser');
	Route::post('likes/customer/like','LikeProductController@like');
	Route::post('likes/customer/dislike','LikeProductController@disLike');
	Route::get('likes/customer','LikeProductController@getLikeOfCustomer');
	Route::get('likes/customer/is_liked','LikeProductController@checkIsLikeOfCustomer');

	Route::get('shipper/order/waiting','ShipperController@getListWaitingOrder');
	Route::get('shipper/order/info/{sale_id}','ShipperController@getProductInfoOfSaleBill');
});




