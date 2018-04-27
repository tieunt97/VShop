<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateDanhGiasTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('danh_gias', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('id_kh')->unsigned();
            $table->integer('id_danh_gia')->unsigned();
            $table->integer('id_sp')->unsigned();
            $table->string('tieu_de')->nullable();
            $table->text('noi_dung')->nullable();
            $table->float('so_sao', 8, 2)->nullable();
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('danh_gias');
    }
}
