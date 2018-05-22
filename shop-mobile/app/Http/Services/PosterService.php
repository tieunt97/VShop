<?php
namespace App\Http\Services;
use Carbon\Carbon;
use App\Poster;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;

class PosterService {

	public function getNewPostersByLimit() {
		$posters = Poster::select('*')->orderBy('created_at')->paginate(10);
		return $posters;
	}

}