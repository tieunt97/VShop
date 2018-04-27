<?php

use Illuminate\Support\Facades\Schema;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Database\Migrations\Migration;

class CreateGiaTriThuocTinhsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('gia_tri_thuoc_tinhs', function (Blueprint $table) {
            $table->increments('id');
            $table->integer('id_sp');
            $table->integer('id_thuoc_tinh_sp');
            $table->text('gia_tri');
            $table->timestamps();

            $table->index('id_sp');
            $table->index('id_thuoc_tinh_sp');
            $table->unique(['id_sp', 'id_thuoc_tinh_sp']);
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('gia_tri_thuoc_tinhs');
    }
}
