<?php

namespace App\Http\Controllers;
use App\Http\Services\ProductService;
use Illuminate\Http\Request;
use App\ProductType;
use App\Http\Services\TypeProductService;

class HomeController extends AppBaseController
{
    protected $productService;
    protected $typeProductService;

    public function __construct(ProductService $productService, TypeProductService $typeProductService)
    {
    	$this->productService = $productService;
    	$this->typeProductService = $typeProductService;
    }

    public function layoutHomePage() {
    	$products = [];
    	$productTypes = ProductType::all()->pluck('id')->toArray();
    	for ($product_type_id = 0; $product_type_id < count($productTypes); $product_type_id++) {
    		$products[] = [
    			'type_id' => $productTypes[$product_type_id],
    			'products'	=> $this->productService->getAllProductsByType($productTypes[$product_type_id])
    		];
    	}
    	return $products;
    }

    public function getProductByType($productTypeId) {
    	$products = $this->productService->getAllProductsByType($productTypeId);
    	return $this->sendResponse($products, '200');
    }

    public function getAllProductType() {
    	$productTypes =  $this->typeProductService->getAllTypes();
    	return $this->sendResponse($productTypes, '200');
    }

}
