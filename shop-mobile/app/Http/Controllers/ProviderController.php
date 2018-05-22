<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Http\Services\ProviderService;

class ProviderController extends AppBaseController
{
    protected $providerService;

    public function __construct(ProviderService $providerService) {

    	$this->providerService = $providerService;
    	
    }

    public function getAllProviders() {
    	return $this->sendResponse($this->providerService->getProvidersInfo(), '200');
    }
}
