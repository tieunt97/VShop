<?php

namespace App\Http\Controllers;
use App\Http\Services\PosterService;
use Illuminate\Http\Request;
use App\Consts;
use App\Product;
use App\Provider;

class PosterController extends AppBaseController
{
    protected $posterService;

    public function __construct(PosterService $posterService)
    {
    	$this->posterService = $posterService;
    }

    public function getNewPostersByLimit() {
		$posters = $this->posterService->getNewPostersByLimit();
		return $posters;
    }
}
