<?php

use Illuminate\Database\Seeder;
use Faker\Factory as Faker;
use App\User;

class PosterTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        $posters = [];
        $faker = Faker::create();
        $RECORD_COUNT =500;
        $employees = User::select('id')->where('level','=',3)->pluck('id')->toArray();
        for ($poster_id = 0; $poster_id < $RECORD_COUNT; $poster_id ++) {
	        $evaluations[] = [
    			'employee_id' => $faker->randomElement($employees),
    			'title' => $faker-> sentence($nbWords = 10, $variableNbWords = true),
    			'content'	=>	$faker->text(1000),
    			'created_at'	=> $faker->dateTimeBetween($startDate = '-2 years', $endDate = 'now', $timezone = null)
			];
	        	
        }

        foreach (array_chunk($evaluations, 500) as $data) {
        	DB::table('posters')->insert($data);
        }
    }

}
