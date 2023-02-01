package functional.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.LongStream;


public class StreamFilesAndThreads {

	static String path = "resources/SomeFile.txt";
	public static void main (String[] args) throws IOException {
		StreamFilesAndThreads.parallelStreams();
		StreamFilesAndThreads.getContentOfFile();
		StreamFilesAndThreads.getAllFilesFromRoot();
		StreamFilesAndThreads.getOnlyDirectories();
		StreamFilesAndThreads.threadFunctionalApproach();
	}

	private static void parallelStreams(){
		long time = System.currentTimeMillis();
		System.out.println(
				//LongStream.range(0,1000000).sum() //without use of multi-core processor in machine
				LongStream.range(0,1000000).parallel().sum()
		);
		System.out.println(System.currentTimeMillis() - time);
	}
	private static void getContentOfFile() throws IOException {
		Files.lines(Paths.get(path))
				.map(str -> str.split(" "))
				.flatMap(Arrays :: stream)
				.distinct().sorted()
				.forEach(System.out :: println);
	}

	private static void getAllFilesFromRoot() throws IOException {
		Files.list(Paths.get("."))
				.forEach(System.out :: println);
	}

	private static void getOnlyDirectories() throws IOException {
		Files.list(Paths.get("."))
				.filter(Files :: isDirectory)
				.forEach(System.out :: println);
	}

	private static void threadsTraditionalApproach(){
		Runnable runnable = new Runnable() {
			@Override
			public void run () {
				for(int i = 0; i < 10000; i++){
					System.out.println(Thread.currentThread().getId() + " : " + i);
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}

	private static void threadFunctionalApproach(){

		Runnable runnable = () -> IntStream.range(0,10000).
				forEach( i -> System.out.println(Thread.currentThread().getId() + " : " + i));
		Thread thread = new Thread(runnable);
		thread.start();
	}
}
