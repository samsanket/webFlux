package com.sanketdd.webFlux;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.swing.*;
import java.util.ArrayList;

import static reactor.core.publisher.Flux.just;

public class MonoFluxTest {

    @Test
    public void testMono() {
        Mono<String> stringMono = Mono.just("sanket");// declared publisher
        stringMono.subscribe(System.out::println);  // subsrcribed to the publisher
    }

    @Test
    public void checkLogs() {
        Mono<String> stringMono = Mono.just("sanket").log();// add logs on  publisher
        stringMono.subscribe(System.out::println);  // subsrcribed to the publisher
    }

    /*
    15:39:01.689 [main] INFO reactor.Mono.Just.1 -- | onSubscribe([Synchronous Fuseable] Operators.ScalarSubscription)
15:39:01.692 [main] INFO reactor.Mono.Just.1 -- | request(unbounded)
15:39:01.692 [main] INFO reactor.Mono.Just.1 -- | onNext(sanket)
sanket
15:39:01.693 [main] INFO reactor.Mono.Just.1 -- | onComplete()

     */


    @Test
    public void CheckError() {
        Mono<?> stringMono = Mono.just("sanket").then(Mono.error(new RuntimeException("Exceptin orccoured "))).log();//
        stringMono.subscribe(System.out::println);
    }


/*15:39:25.218 [main] INFO reactor.Mono.IgnoreThen.1 -- onSubscribe(MonoIgnoreThen.ThenIgnoreMain)
15:39:25.222 [main] INFO reactor.Mono.IgnoreThen.1 -- request(unbounded)
15:39:25.224 [main] ERROR reactor.Mono.IgnoreThen.1 -- onError(java.lang.RuntimeException: Exceptin orccoured )
15:39:25.224 [main] ERROR reactor.Mono.IgnoreThen.1 --
java.lang.RuntimeException: Exceptin orccoured
*/


    @Test
    public void CheckErrorPrint() {
        Mono<?> stringMono = Mono.just("sanket").then(Mono.error(new RuntimeException("Excepting coloured "))).log();//
        stringMono.subscribe(System.out::println, (e) -> System.out.println(e.getMessage() + "\n" + e.getClass() + "\n" + e.getCause()));
    }


    @Test
    public void testFlux() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int i = 0;
        while (i != 10) {
            arrayList.add(i);
            i++;
        }

        Flux<ArrayList<Integer>> arrayListFlux = Flux.just(arrayList).concatWithValues().log();

        arrayListFlux.subscribe(System.out::println);

    }

//    16:18:27.764 [main] INFO reactor.Flux.ConcatArray.1 -- onSubscribe(FluxConcatArray.ConcatArraySubscriber)
//16:18:27.768 [main] INFO reactor.Flux.ConcatArray.1 -- request(unbounded)
//16:18:27.769 [main] INFO reactor.Flux.ConcatArray.1 -- onNext([0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
//[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
//            16:18:27.769 [main] INFO reactor.Flux.ConcatArray.1 -- onComplete()

    @Test
    public void testFluxError() {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int i = 0;
        while (i != 10) {
            arrayList.add(i);
            i++;
        }

        Flux<ArrayList<Integer>> arrayListFlux = Flux.just(arrayList)
                .concatWith(Flux.error(new RuntimeException("SomeThing Went Wrong !!"))).concatWithValues()
                .log();

        arrayListFlux.subscribe(System.out::println,(e)->System.out.println(e.getMessage()));

    }

    /*16:22:05.783 [main] INFO reactor.Flux.ConcatArray.1 -- onSubscribe(FluxConcatArray.ConcatArraySubscriber)
16:22:05.786 [main] INFO reactor.Flux.ConcatArray.1 -- request(unbounded)
16:22:05.786 [main] INFO reactor.Flux.ConcatArray.1 -- onNext([0, 1, 2, 3, 4, 5, 6, 7, 8, 9])
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
16:22:05.787 [main] ERROR reactor.Flux.ConcatArray.1 -- onError(java.lang.RuntimeException: SomeThing Went Wrong !!)
16:22:05.787 [main] ERROR reactor.Flux.ConcatArray.1 -- */
}



