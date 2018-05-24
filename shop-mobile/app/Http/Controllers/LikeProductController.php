<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Services\LikeProductService;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;

class LikeProductController extends AppBaseController
{   
    protected $likeProductService;

    public function __construct(LikeProductService $likeProductService)
    {
        $this->likeProductService = $likeProductService;
    }
  

    public function getLikeOfCustomer()
    {
        $user = Auth::user();
        $likeProducts = $this->likeProductService->getLikeOfCustomer($user->id);
        return $this->sendResponse($likeProducts, '200');
    }

    public function checkIsLikeOfCustomer(Request $request) {
        $product_id = $request->product_id;
        $user = Auth::user();
        $isLike = $this->likeProductService->checkCustommerLikedProduct($user->id, $product_id);
        return $this->sendResponse($isLike, '200');
    }

    public function like(Request $request) {
        $user = Auth::user();
        $product_id = $request->product_id;
        $isLiked = $this->likeProductService->checkCustommerLikedProduct($user->id, $product_id);
        if ($isLiked) return $this->sendError('this product is liked!');
        DB::beginTransaction();
        try {
            DB::insert('insert into like_product_lists (customer_id, product_id) values (?,?)',[$user->id, $product_id]);
            DB::commit();
            return $this->sendResponse(null, 'liked');
         } catch (\Exception $e) {
            DB::rollBack();
            return $this->sendError(null, $e->getMessage());
        }
    }

    public function disLike(Request $request) {
        $user = Auth::user();
        $product_id = $request->product_id;
        if (!$this->likeProductService->checkCustommerLikedProduct($user->id, $product_id)) return $this->sendError('this product is disliked!');
        DB::beginTransaction();
        try {
            DB::table('like_product_lists')->where('customer_id','=',$user->id)->where('product_id','=',$product_id)->delete();
            DB::commit();
            return $this->sendResponse(null, 'disliked');
         } catch (\Exception $e) {
            DB::rollBack();
            return $this->sendError(null, $e->getMessage());
        }
    }
}
