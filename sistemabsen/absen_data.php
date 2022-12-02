<?php
	require_once __DIR__ . '/koneksi.php';
	$db = new DB_CONNECT();

	header ("Content-Type:application/json");   

	$parameter = array(
		'nik' => $_POST ['nik'],
		'tanggal' => $_POST['tanggal'],
		'waktumasuk' => $_POST['waktumasuk'],
		'waktukeluar' => $_POST['waktukeluar'],
		'action' => $_POST['action']
	);

	$response = array(
		'error' => FALSE,
		'error_text' => ""
	);

	if($response['error'] == FALSE){
		if($parameter['action'] == ""){
			$response['error'] = TRUE;
			$response['error_text'] = "Parameter tidak lengkap";
		}
	}

	if($response['error'] == FALSE){
		switch($parameter['action']){
			case "in":
			
				$cek = "SELECT * from `tb_absenkaryawan` WHERE `waktumasuk` IS NOT NULL AND `nik` = '".$parameter['nik']."' AND `tanggal` = '".$parameter['tanggal']."'";
				$result = mysqli_query($db->connect(), $cek);
				//$row = mysql_fetch_array($result);
			
				if (mysqli_num_rows($result) > 0) {
					$response['error'] = TRUE;
					$response['error_text'] = "Sudah Absen Masuk";
				}
				else{
					
					//$kuery = "INSERT INTO `tb_absenkaryawan` (`idabsen`, `tanggal`, `waktumasuk`, `nik`) VALUES ('','".$parameter['tanggal']."','".$parameter['waktumasuk']."','".$parameter['nik']."')";
					$kuery = "INSERT INTO `tb_absenkaryawan` (`tanggal`, `waktumasuk`, `nik`) VALUES ('".$parameter['tanggal']."','".$parameter['waktumasuk']."','".$parameter['nik']."')";
					mysqli_query($db->connect(), $kuery);
					header("HTTP/1.1 200");
					$response['error'] = FALSE;
					$response['error_text'] = "Absen Masuk Berhasil";
				}
			$db->close();
			break;
			
			case "out":
			
				/*$cek = "SELECT 
						CASE
							WHEN waktukeluar is null THEN 1
							WHEN waktukeluar is not null THEN 2
						END AS Cek
						FROM tb_absenkaryawan
						WHERE nik = '".$parameter['nik']."' AND tanggal = '".$parameter['tanggal']."'"; */
				
				$cek = "SELECT 
						waktukeluar
						FROM tb_absenkaryawan
						WHERE nik = '".$parameter['nik']."' AND tanggal = '".$parameter['tanggal']."'";
						
				$result = mysqli_query($db->connect, $cek);
				/*$row = mysql_fetch_array($result);
					
				if($row['Cek']==1){
					$kuery = "UPDATE tb_absenkaryawan SET waktukeluar = '".$parameter['waktukeluar']."' WHERE nik = '".$parameter['nik']."'";
					mysqli_query($db->connect(), $kuery);
					header("HTTP/1.1 200");
					$response['error'] = FALSE;
					$response['error_text'] = "Absen Keluar Berhasil";
				}
				else if($row['Cek']==2){
					$response['error'] = TRUE;
					$response['error_text'] = "Sudah Absen Keluar";
				}
				else{
					$response['error'] = TRUE;
					$response['error_text'] = "Belum Absen Masuk";
				}*/				
				
				if (mysqli_num_rows($result) == 0 ){
					$kuery = "UPDATE tb_absenkaryawan SET waktukeluar = '".$parameter['waktukeluar']."' WHERE nik = '".$parameter['nik']."'";
					mysqli_query($db->connect(), $kuery);
					header("HTTP/1.1 200");
					$response['error'] = FALSE;
					$response['error_text'] = "Absen Keluar Berhasil";
				}
				else if (mysqli_num_rows($result) > 0){
					$response['error'] = TRUE;
					$response['error_text'] = "Sudah Absen Keluar";
				}
				else {
					$response['error'] = TRUE;
					$response['error_text'] = "Belum Absen Masuk";
				}
				
			$db->close();
			break;
		}
	}
			
	echo json_encode($response);
