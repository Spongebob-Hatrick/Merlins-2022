package com.ftc11392.sequoia.task;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * A {@link Task} that will only initialize, running the given {@link Runnable}. Stops immediately.
 */
public class InstantTask extends Task {
	private final Runnable toRun;

	public InstantTask(Runnable toRun, Telemetry telemetry) {
		super(telemetry);
		this.toRun = toRun;
		running = false;
	}

	@Override
	public void init() {
		toRun.run();
	}

	@Override
	public void loop() {
		// Empty as there is no desired behavior here
	}

	@Override
	public void stop(boolean interrupted) {
		// Empty as there is no desired behavior here
	}
}
