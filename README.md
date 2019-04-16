# Desafio-tecnico-codificar
Minha tentativa do desafio técnico para o processo seletivo da vaga de estágio da Codificar.
Cada branch irá possuir dados referentes a partes do sistema, segue abaixo uma esquematização simples do significado de cada branch e seu conteúdo:
<ul>
	<li>Banco de dados</li>
	<ul>
		<li>O branch terá um arquivo com o esqueleto da camada de modelo, ou seja, a forma padrão que a camada modelo irá ter.</li>
		<li>A camada modelo utilizará da tecnologia PostgreSQL.</li>
	</ul>
	<li>Bot</li>
	<ul>
		<li>O branch conterá o programa enquanto eu estiver trabalhando nele.</li>
	</ul>
	<li>master</li>
	<ul>
		<li>O branch conterá o resultado final</li>
	</ul>
</ul>

Método de instalação:
<ul>
	<li>Asegure-se de que você tem a versão mais recente do PostgreSQL Server (v. 10) instalada</li>
	<li>Abra o gerenciador do Postgres (i.e.: PGAdmin 4 ou outro aplicativo)</li>
	<ul>
		<li>Recomendo o PGAdmin 4, já que este consegue lidar bem com a versão mais recente do PostgreSQL Server.</li>
	</ul>
	<li>Faça um banco de dados chamado "DeOlho" (sem as aspas) e "restaure" esse banco de dados para o estado encontrado no arquivo <i>backup.backup</i>.</li>
	<ul>
		<li>Geralmente existe um comando quando se clica com o botão direito do mouse no banco de dados, no navegador, para restaura-lo para um estado anterior.</li>
		<li>Esse comando geralmente pede o arquivo de backup do estado anterior.</li>
	</ul>
	<li>Abra a IDE Java de sua preferência e carregue o projeto <i>Fetcher</i>.</li>
	<li>Abra o arquivo persistence.xml em META-INF e altere as configurações da PersistenceUnit para que funcionem com as suas configurações de banco de dados (i.e.: senha, usuário, etc.).</li>
	<li>Rode o programa.</li>
</ul>