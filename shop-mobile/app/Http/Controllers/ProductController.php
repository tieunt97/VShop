<?php

namespace App\Http\Controllers;
use App\Http\Services\ProductService;
use Illuminate\Http\Request;
use App\Consts;
use App\Product;
use App\Provider;

class ProductController extends AppBaseController
{
    protected $productService;

     public function __construct(ProductService $productService)
    {
    	$this->productService = $productService;
    }

    public function getProductById($id) {
    	$product = Product::where('id', $id)->first();
        $star_info = $this->productService->getStarNumberOfProduct($id);
        $provider = Provider::where('id', '=', $product->provider)->pluck('name')->first();
        
        $product->star_info = [
            'star_number_average'   => $star_info['star_number'],
            'star_count_average'    => $star_info['star_count'],
            'star_detail'           => $this->productService->getStarNumberDetailOfProduct($id)
        ];
        $product->provider = $provider;
        $product->evaluationInfo = $this->productService->getEvaluations($id, Consts::NUM_FIST_EVALUATION_SHOWED);
        $product->specificationsInfo = $this->productService->getProductSpecificationsOfProduct($id);
    	return $this->sendResponse($product, '200');
    }

    public function getRestOfProductsInStock($product_id) {
        return $this->sendResponse($this->productService->getRestOfProductsInStock($product_id), '200');
    }



    public function checkCustommerLikedProduct(Request $request) {
        $customer_id = $request->customer_id;
        $product_id = $request->product_id;
        $isLiked = $this->productService->checkCustommerLikedProduct($customer_id, $product_id);
        return $this->sendResponse($isLiked, '200');
    }

    public function searchProductBy($keyword) {
    	$products = $this->productService->searchProductBy($keyword);
    	return $this->sendResponse($this->additionStarInfoToProducts($products), '200');
    }

    public function getEvaluationsOfProductId($id) {
        $valuations = $this->productService->getEvaluations($id, Consts::GET_ALL);
        return $this->sendResponse($valuations, '200');
    }

    public function test(Request $request) {
        // $valuations =  $this->productService->sortAndFilterProducts($request);
        // return $this->sendResponse($valuations, '200');
    }

    public function additionStarInfoToProducts($products) {
        foreach ($products as $product) {
            $star_info = $this->productService->getStarNumberOfProduct($product->id);
            $product->star_number = $star_info['star_number'];
            $product->star_count  = $star_info['star_count'];
        }
        return $products;
    }

    


}
