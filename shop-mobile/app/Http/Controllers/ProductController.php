<?php

namespace App\Http\Controllers;
use App\Http\Services\ProductService;
use Illuminate\Http\Request;
use App\Consts;
use App\Product;

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
        $product->star_info = [
            'star_number_average'   => $star_info['star_number'],
            'star_count_average'    => $star_info['star_count'],
            'star_detail'           => $this->productService->getStarNumberDetailOfProduct($id)
        ];
        $product->evaluationInfo = $this->productService->getEvaluations($id, Consts::NUM_FIST_EVALUATION_SHOWED);
    	return $this->sendResponse($product, '200');
    }

    public function searchProductBy($keyword) {
    	$product = $this->productService->searchProductBy($keyword);
    	return $this->sendResponse($product, '200');
    }

    public function getEvaluationsOfProductId($id) {
        $valuations = $this->productService->getEvaluations($id, Consts::GET_ALL);
        return $this->sendResponse($valuations, '200');
    }

    public function test() {
        // return $this->productService->getEvaluations(, 6);
    }

    


}
