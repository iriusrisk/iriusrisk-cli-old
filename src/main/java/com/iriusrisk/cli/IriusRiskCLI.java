package com.iriusrisk.cli;

import picocli.CommandLine;

@CommandLine.Command(
        subcommands = {
                ProductsCommand.class
        }
)
public class IriusRiskCLI implements Runnable {
    @CommandLine.Spec
    CommandLine.Model.CommandSpec spec;

    private int executionStrategy(CommandLine.ParseResult parseResult) {
        init(); // custom initialization to be done before executing any command or subcommand
        return new CommandLine.RunLast().execute(parseResult); // default execution strategy
    }

    private void init() {
        // ...
    }

    @Override
    public void run() {
        throw new CommandLine.ParameterException(spec.commandLine(), "Specify a subcommand");
    }

    public static void main(String[] args) {
        IriusRiskCLI cli = new IriusRiskCLI();
        new CommandLine(cli)
                .setExecutionStrategy(cli::executionStrategy)
                .execute(args);
    }
}