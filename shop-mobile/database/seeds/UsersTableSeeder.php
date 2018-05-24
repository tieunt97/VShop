<?php

use Illuminate\Database\Seeder;
use Carbon\Carbon;
use Faker\Factory as Faker;
class UsersTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $users = [];
        $faker = Faker::create();
        $RECORD_COUNT =400;

        for ($user_id = 1; $user_id <= $RECORD_COUNT; $user_id ++) {

        	if($user_id > 30) {
        		$users[] = [
        		'id' => $user_id,
        		'name' => $faker->name,
        		'phone_number' => $faker->phoneNumber,
                'password' => bcrypt('123456'),
                'address'       => $faker->address,
        		'email'	=> $faker->email,
                'level' => 1,
                'api_token' => bin2hex(random_bytes(32)),
        		'created_at' => Carbon::now()
        	];

            }else if($user_id > 25) {
                $users[] = [
                'id' => $user_id,
                'name' => $faker->name,
                'phone_number' => $faker->phoneNumber,
                'password' => bcrypt('123456'),
                'address'       => $faker->address,
                'email' => $faker->email,
                'level' => 2,
                'api_token' => bin2hex(random_bytes(32)),
                'created_at' => Carbon::now()
            ];

            }else if($user_id > 20) {
                $users[] = [
                'id' => $user_id,
                'name' => $faker->name,
                'phone_number' => $faker->phoneNumber,
                'password' => bcrypt('123456'),
                'address'       => $faker->address,
                'email' => $faker->email,
                'level' => 3,
                'api_token' => bin2hex(random_bytes(32)),
                'created_at' => Carbon::now()
            ];

        	}else if($user_id > 2) {
        		$users[] = [
        		'id' => $user_id,
        		'name' => $faker->name,
        		'phone_number' => $faker->phoneNumber,
                'password' => bcrypt('123456'),
                'address'       => $faker->address,
        		'email'	=> $faker->email,
                'level' => 4,
                'api_token' => bin2hex(random_bytes(32)),
        		'created_at' => Carbon::now()
        	];
        	}else {
        		$users[] = [
        		'id' => $user_id,
        		'name' => $faker->name,
        		'phone_number' => $faker->phoneNumber,
                'password' => bcrypt('123456'),
                'address'       => $faker->address,
        		'email'	=> $faker->email,
                'level' => 5,
                'api_token' => bin2hex(random_bytes(32)),
        		'created_at' => Carbon::now()
        	];
        	}
        	
        }

        foreach (array_chunk($users, 500) as $data) {
        	DB::table('users')->insert($data);
        }
    }


}
