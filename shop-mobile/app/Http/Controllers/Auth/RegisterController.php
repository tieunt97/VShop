<?php

namespace App\Http\Controllers\Auth;

use Illuminate\Http\Request;
use App\User;
use DB;
use Exception;
use App\Http\Controllers\AppBaseController;
use Illuminate\Support\Facades\Validator;

class RegisterController extends AppBaseController
{
     protected function validator(array $data)
    {
        $rules = User::$rules;
        return Validator::make($data, $rules);
    }

    /**
     * Create a new user instance after a valid registration.
     *
     * @param  array  $data
     * @return User
     */

    public function register(Request $request) {

    	 // $validator = $this->validator($request->all());

      //   if ($validator->fails()) {
      //       return "fail";
      //   }
    	DB::beginTransaction();
    	try {
    		$user = $this->create($request);
    		DB::commit();
    	}catch(Exception $e) {
    		DB::rollback();
    		throw($e);
    	}
    	return $this->sendResponse($user, '200');

    }

    public function test(Request $request) {
    	return $request->all();
    }

    protected function create(Request $request)
    {	
    	$data = $request->all();
        return User::create([
            'name' => $data['name'],
            'email' => $data['email'],
            'level'=> $data['level'],
            'password' => bcrypt($data['password']),
            'api_token' => bin2hex(random_bytes(32))
        ]);
    }
}
