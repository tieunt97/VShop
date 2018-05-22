<?php

use Illuminate\Database\Seeder;

class DatabaseSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        // $this->call(UsersTableSeeder::class);
        $this->call(ProductTypeTableSeeder::class);
        $this->call(ProviderTableSeeder::class);
        $this->call(UsersTableSeeder::class);
        $this->call(ProductTableSeeder::class);
        $this->call(CategoryAttributeTableSeeder::class);
        $this->call(AttributeValueTableSeeder::class);
        $this->call(EvaluationTableSeeder::class);
        $this->call(LikeProductListTableSeeder::class);
        $this->call(SaleBillsTableSeeder::class);
        $this->call(SaleDescriptionTableSeeder::class);
        $this->call(PosterTableSeeder::class);
    }
}
