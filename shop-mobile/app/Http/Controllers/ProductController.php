<?php

namespace App\Http\Controllers;
use App\Http\Services\ProductService;
use Illuminate\Http\Request;
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
        $product->star_number = $star_info['star_number'];
        $product->star_count  = $star_info['star_count'];
    	return $this->sendResponse($product, '200');
    }

    public function searchProductBy($keyword) {
    	$product = $this->productService->searchProductBy($keyword);
    	return $this->sendResponse($product, '200');
    }

    


}
