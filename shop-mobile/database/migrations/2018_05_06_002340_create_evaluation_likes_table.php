<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateEvaluationLikesTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('evaluation_likes', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('customer_id');
            $table->integer('evaluation_id');
            $table->timestamps();

            $table->index('customer_id');
            $table->index('evaluation_id');
            $table->unique(['customer_id','evaluation_id']);
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('evaluation_likes');
    }
}
