<?php
namespace App\Http\Services;
use Carbon\Carbon;
use App\Poster;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Log;

class PosterService {

	public function getNewPostersByLimit($limit) {
		if ($limit == 'all') {
			$posters = Poster::select('*')->orderBy('created_at')->paginate(10);
		}else {
			$posters = Poster::select('*')->orderBy('created_at')->limit($limit)->paginate(10);
		}
		
		return $posters;
	}

}